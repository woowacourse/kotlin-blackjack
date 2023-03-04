package blackjack.controller

import blackjack.domain.BlackJackReferee
import blackjack.domain.Cards
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

    //TODO: DrawState를 외부에서 판단하여 계산하지말고 메세지만 던지는 구조로
    private fun askToDrawAdditionalCardForPlayer(player: Player) {
        do {
            val drawFlag = InputView.requestAdditionalDraw(player)
        } while (drawFlag && drawAdditionalCardForPlayer(player) == DrawState.POSSIBLE)

        //TODO: 외부에서 값을 꺼내쓰지말고 메세지를 던져 객체가 일하도록
        if (player.cards.size == Cards.INITIAL_CARDS_SIZE) {
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

    //TODO: 이 로직은 내가 생각했을때 도메인에 어울리는 것 같다!
    private fun judgeGameResults() {
        val playersGameResult = blackJackReferee.judgePlayerGameResults(players, dealer)
        val dealerGameResult = playersGameResult.map { playerGameResult ->
            !playerGameResult.gameResult
        }

        OutputView.printGameResults(playersGameResult, dealerGameResult)
    }
}
