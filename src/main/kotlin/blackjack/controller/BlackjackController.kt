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
        val players = initPlayers()
        val blackjackGame = BlackjackGame(dealer, players, cardProvider)
        outputView.printInitCard(dealer, players)

        val gameResult =
            blackjackGame.start(
                { inputView.readMoreCardDecision(it) },
                { outputView.printPlayerCardsMessage(it) },
                { outputView.printDealerAdditionalCardMessage() },
            )

        showGameResult(dealer, players, gameResult)
    }

    private fun initPlayers(): Players {
        val playersName = inputView.readPlayersName()
        val playersBattingAmount = inputView.readPlayersBattingAmount(playersName)
        val players = retryWhileNotException { Players.of(playersName, playersBattingAmount) }
        return players
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
        gameResult: GameResultStorage,
    ) {
        outputView.printPlayersCardResult(dealer, players)
        outputView.printFinalProfitResult(dealer, players)
    }
}
