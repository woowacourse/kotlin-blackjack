package domain

import domain.card.CardDeck
import domain.player.Dealer
import domain.player.Player

const val INITIAL_CARDS_SIZE = 2
const val DEFAULT_DEALER_NAME = "딜러"

class BlackJackGame(val gamblers: List<Player>,
                    private val dealer: Dealer = Dealer(DEFAULT_DEALER_NAME),
                    private val cardDeck: CardDeck = CardDeck()) {

    private val players = gamblers.plus(dealer)

    fun giveInitialCards(): List<Player> {
        players.forEach {
            givePlayerCard(it, INITIAL_CARDS_SIZE)
        }
        return players
    }

    fun giveDealerAdditionalCards() :Dealer{
        while (dealer.shouldDraw()) {
            givePlayerCard(dealer)
        }
        return dealer
    }

    fun givePlayerCard(player: Player, count: Int = 1): Player {
        player.receiveCards(cardDeck.drawCards(count))
        return player
    }

    fun gameResult(): List<Player> {
        gamblers.forEach {
            dealer.compare(it)
        }
        return players
    }
}