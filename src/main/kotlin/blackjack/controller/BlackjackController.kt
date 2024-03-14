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
        val players = retryWhileNotException { Players.from(inputView.readPlayersName()) }
        val blackjackGame = BlackjackGame(dealer, players, cardProvider)
        outputView.printInitCard(dealer, players)

        val gameResult =
            blackjackGame.start(
                { inputView.readMoreCardDecision(it) },
                { outputView.printPlayerCardsMessage(it) },
                { outputView.printDealerAdditionalCardMessage() },
            )

        outputView.printPlayersCardResult(dealer, players)
        outputView.printFinalGameResult(players, gameResult)
    }
}
