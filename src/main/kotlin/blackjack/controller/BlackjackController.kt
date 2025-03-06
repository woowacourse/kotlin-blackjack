package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.CardsMaker
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val cardDeck = CardDeck(CardsMaker.CARDS)

    fun run() {
        outputView.printStartMessage()
        val players: List<Player> = inputView.readPlayers()
        val dealer = Dealer()

        getCards(players, dealer)
    }

    private fun getCards(
        players: List<Player>,
        dealer: Dealer,
    ) {
        repeat(2) {
            getCardsToPlayer(players)
            getCardsToDealer(dealer)
        }
        outputView.printPlayersCards(dealer, players)
    }

    private fun getCardsToDealer(dealer: Dealer) {
        val card = cardDeck.pickCard()
        dealer.appendCard(card)
    }

    private fun getCardsToPlayer(players: List<Player>) {
        players.forEach { player ->
            val card = cardDeck.pickCard()
            player.appendCard(card)
        }
    }
}
