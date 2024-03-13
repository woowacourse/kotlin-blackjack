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

        takePlayersTurn(blackjackGame)
        takeDealerTurn(blackjackGame)
        showGameResult(dealer, players, blackjackGame)
    }

    private fun takePlayersTurn(blackjackGame: BlackjackGame) {
        blackjackGame.startPlayersTurn(
            { player -> inputView.readMoreCardDecision(player) },
            { player -> outputView.printPlayerCardsMessage(player) },
        )
    }

    private fun takeDealerTurn(blackjackGame: BlackjackGame) {
        blackjackGame.startDealerTurn {
            outputView.printDealerAdditionalCardMessage()
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
        blackjackGame: BlackjackGame,
    ) {
        val gameResultStorage = blackjackGame.calculateGameResult()
        outputView.printPlayersCardResult(dealer, players)
        outputView.printFinalGameResult(players, gameResultStorage)
    }
}
