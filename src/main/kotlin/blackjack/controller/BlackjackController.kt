package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.Card
import blackjack.model.CardProvider
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
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

        initHandCards(dealer, players)
        takePlayersTurn(players)
        takeDealerTurn(dealer)
        showGameResult(dealer, players)
    }

    private fun initHandCards(
        dealer: Dealer,
        players: Players,
    ) {
        BlackjackGame.initCard(dealer, players, cardProvider)
        outputView.printInitCard(dealer, players)
    }

    private fun takePlayersTurn(players: Players) {
        players.playerGroup.forEach {
            takePlayerTurn(it)
        }
    }

    private fun takePlayerTurn(player: Player) {
        while (player.decideMoreCard()) {
            val inputMoreCardDecision = inputView.readMoreCardDecision(player)
            if (!inputMoreCardDecision) break

            player.receiveCard(Card.from(cardProvider))
            outputView.printPlayerCardsMessage(player)
        }
    }

    private fun takeDealerTurn(dealer: Dealer) {
        while (dealer.decideMoreCard()) {
            dealer.receiveCard(Card.from(cardProvider))
            outputView.printDealerAdditionalCardMessage()
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        val gameResultStorage = BlackjackGame.calculateGameResult(dealer, players)
        outputView.printPlayersCardResult(dealer, players)
        outputView.printFinalGameResult(players, gameResultStorage)
    }
}
