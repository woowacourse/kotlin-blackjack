package domain

import domain.card.CardDeck
import domain.player.Dealer
import domain.player.Player

const val INITIAL_CARDS_SIZE = 2
const val DEFAULT_DEALER_NAME = "딜러"

class BlackJackGame(
    private val gamblers: List<Player>,
    val dealer: Dealer = Dealer(DEFAULT_DEALER_NAME),
    private val cardDeck: CardDeck = CardDeck()
) {

    val players = gamblers.plus(dealer)

    fun giveInitialCards() = players.forEach {
        giveCard(it, INITIAL_CARDS_SIZE)
    }

    fun giveDealerAdditionalCards() {
        while (dealer.shouldDraw()) {
            giveCard(dealer)
        }
    }

    fun giveCard(player: Player, count: Int = 1) {
        player.receiveCards(cardDeck.drawCards(count))
    }

    fun findGamblerByName(name: String): Player {
        return gamblers.first {
            it.name == name
        }
    }

    fun endGame() = gamblers.forEach {
        dealer.compete(it)
    }
}
