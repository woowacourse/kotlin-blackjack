package blackjack.model.playing.participants

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.PlayerName

abstract class Role(open val name: PlayerName, open val cardHand: CardHand) {
    abstract fun canDraw(): Boolean

    fun addInitialCards(cardDeck: CardDeck) {
        repeat(INITIAL_CARDS_COUNT) {
            cardHand.addNewCard(cardDeck.draw())
        }
    }

    fun draw(card: Card) {
        cardHand.addNewCard(card)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
