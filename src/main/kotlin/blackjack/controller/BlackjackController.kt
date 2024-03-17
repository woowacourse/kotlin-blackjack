package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.card.CardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import blackjack.model.result.GameResultStorage
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

        val gameResult = startBlackjackGame(blackjackGame)
        showResult(dealer, players, gameResult)
    }

    private fun startBlackjackGame(blackjackGame: BlackjackGame): GameResultStorage {
        val gameResult =
            blackjackGame.start(
                { inputView.readMoreCardDecision(it) },
                { outputView.printPlayerCardsMessage(it) },
                { outputView.printDealerAdditionalCardMessage() },
            )
        return gameResult
    }

    private fun showResult(
        dealer: Dealer,
        players: Players,
        gameResult: GameResultStorage,
    ) {
        outputView.printPlayersCardResult(dealer, players)
        outputView.printFinalProfitResult(gameResult)
    }
}
