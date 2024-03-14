package blackjack.model.playing.participants

import blackjack.model.card.CardDeck
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.player.PlayerName

abstract class Role(open val name: PlayerName, open val cardHand: CardHand) {
    abstract fun getState(): CardHandState

    abstract fun canDraw(): Boolean

    fun addInitialCards(cardDeck: CardDeck) {
        repeat(INITIAL_CARDS_COUNT) {
            cardHand.addNewCard(cardDeck)
        }
    }

    fun draw(cardDeck: CardDeck) {
        cardHand.addNewCard(cardDeck)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
