package domain

import domain.card.CardDeck
import domain.player.Dealer
import domain.player.Player

const val INITIAL_CARDS_SIZE = 2
const val DEFAULT_DEALER_NAME = "딜러"

class BlackJackGame(val gamblers: List<Player>,
                    val dealer: Dealer = Dealer(DEFAULT_DEALER_NAME),
                    private val cardDeck: CardDeck = CardDeck()) {

    val players = gamblers.plus(dealer)

    fun giveInitialCards() {
        players.forEach {
            givePlayerCard(it, INITIAL_CARDS_SIZE)
        }
    }

    fun giveDealerAdditionalCards() {
        while (dealer.shouldDraw()) {
            givePlayerCard(dealer)
        }
    }

    fun givePlayerCard(player: Player, count: Int = 1) {
        player.receiveCards(cardDeck.drawCards(count))
    }

    fun endGame() {
        gamblers.forEach {
            dealer.compare(it)
        }
    }

    fun findGamblerByName(name: String): Player {
        return gamblers.first {
            it.name == name
        }
    }
}