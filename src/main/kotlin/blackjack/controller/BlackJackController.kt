package blackjack.controller

import blackjack.domain.BlackJackReferee
import blackjack.domain.Dealer
import blackjack.domain.DrawState
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val dealer: Dealer = Dealer(),
    private val blackJackReferee: BlackJackReferee = BlackJackReferee()
) {

    private val players: List<Player> by lazy {
        InputView.requestPlayersName().map { name ->
            Player(name)
        }
    }

    fun run() {
        showDividingCards()
        drawAdditionalCards()
        drawAdditionalCardForDealer()
        showFinalCards()
        judgeGameResults()
    }

    private fun showDividingCards() = OutputView.printCardDividingMessage(dealer, players)

    private fun drawAdditionalCards() = players.forEach { player ->
        askToDrawAdditionalCard(player)
    }

    // TODO: 상수 분리
    private fun askToDrawAdditionalCard(player: Player) {
        do {
            val drawFlag = InputView.requestAdditionalDraw(player)
        } while (drawFlag == "y" && drawAdditionalCardForPlayer(player) == DrawState.POSSIBLE)

        if (player.cards.cards.size == 2) {
            OutputView.printCardResults(player)
        }
    }

    private fun drawAdditionalCardForPlayer(player: Player): DrawState {
        val drawState = player.drawCard()

        OutputView.printCardResults(player)

        return drawState
    }

    private fun drawAdditionalCardForDealer() {
        val drawResult = dealer.drawCard()

        OutputView.printIsDealerReceivedCard(drawResult)
    }

    private fun showFinalCards() = OutputView.printFinalCards(dealer, players)

    private fun judgeGameResults() {
        val playersGameResult = blackJackReferee.judgeGameResult(players, dealer)
        val dealerGameResult = playersGameResult.map { playerGameResult ->
            !playerGameResult.gameResult
        }

        OutputView.printGameResults(playersGameResult, dealerGameResult)
    }
}
