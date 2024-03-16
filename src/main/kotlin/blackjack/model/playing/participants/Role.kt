package blackjack.model.playing.participants

import blackjack.model.card.Card
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.PlayerName

abstract class Role(open val name: PlayerName, open val cardHand: CardHand) {
    abstract fun canDraw(): Boolean

    fun addInitialCards(card: Card) {
        repeat(INITIAL_CARDS_COUNT) {
            cardHand.addNewCard(card)
        }
    }

    fun draw(card: Card) {
        cardHand.addNewCard(card)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
