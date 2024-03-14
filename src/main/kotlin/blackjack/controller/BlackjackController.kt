package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.card.CardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import blackjack.util.retryWhileNotException
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardProvider: CardProvider,
) {
    fun run() {
        val dealer = Dealer()
        val players =
            Players(
                retryWhileNotException { inputView.readPlayersName() },
            ) { retryWhileNotException { inputView.readPlayersBattingAmount(it) } }
        val blackjackGame = BlackjackGame(dealer, players, cardProvider)
        outputView.printInitCard(dealer, players)

        blackjackGame.start(
            { inputView.readMoreCardDecision(it) },
            { outputView.printPlayerCardsMessage(it) },
            { outputView.printDealerAdditionalCardMessage() },
        )

        showGameResult(dealer, players)
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        outputView.printPlayersCardResult(dealer, players)
        outputView.printFinalProfitResult(dealer, players)
    }
}
