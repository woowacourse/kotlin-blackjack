package domain

import domain.card.CardDeck
import domain.player.Dealer
import domain.player.Player

class BlackJackGame(private val players: List<Player>) {

    private val dealer = Dealer("딜러")
    private val cardDeck = CardDeck()

    fun giveInitialCards() {
        for (player in players) {
            givePlayerCard(player, 2)
        }
        givePlayerCard(dealer, 2)
    }

    fun givePlayerCard(player: Player, count: Int = 1) {
        repeat(count) {
            player.receiveCard(cardDeck.draw())
        }
    }

    fun giveDealerAdditionalCards() {
        while (dealer.score() <= 16) {
            givePlayerCard(dealer)
        }
    }

    fun gameResult() {
        for (player in players) {
            dealer.compare(player)
        }
    }
}