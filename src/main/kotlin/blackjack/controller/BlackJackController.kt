package blackjack.controller

import blackjack.domain.BlackJackReferee
import blackjack.domain.CardHand
import blackjack.domain.CardPack
import blackjack.domain.Dealer
import blackjack.domain.DrawState
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val cardPack: CardPack = CardPack(),
    private val dealer: Dealer = Dealer(cardPack),
    private val blackJackReferee: BlackJackReferee = BlackJackReferee()
) {

    private val players: List<Player> by lazy {
        InputView.requestPlayersName().map { name ->
            Player(name = PlayerName(name), cardPack)
        }
    }

    fun run() {
        runCatching {
            showDividingCards()
            drawAdditionalCards()
            drawAdditionalCardForDealer()
            showFinalCards()
            judgeGameResults()
        }.onFailure { exception ->
            OutputView.printErrorMessage(exception)
        }
    }

    private fun showDividingCards() = OutputView.printCardDividingMessage(dealer, players)

    private fun drawAdditionalCards() = players.forEach { player ->
        askToDrawAdditionalCardForPlayer(player)
    }

    private fun askToDrawAdditionalCardForPlayer(player: Player) {
        do {
            val drawFlag = InputView.requestAdditionalDraw(player)
        } while (drawFlag && drawAdditionalCardForPlayer(player) == DrawState.POSSIBLE)

        if (player.cardHand.size == CardHand.INITIAL_CARDS_SIZE) {
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
