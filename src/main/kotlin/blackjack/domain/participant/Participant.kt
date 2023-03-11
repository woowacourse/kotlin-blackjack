package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards

abstract class Participant() {
    val cards = Cards()

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun canDraw(): Boolean

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun drawUntilPossible(deck: CardDeck, onDraw: (Participant) -> Unit) {
        while (canDraw()) {
            cards.add(deck.draw())
            onDraw(this)
        }
    }

    fun getTotalScore(): Int = cards.calculateTotalScore()

    fun getCards(): List<Card> = cards.items

    fun isBlackjack(): Boolean = cards.isBlackJack()

    fun isBust(): Boolean = cards.isBust()
}
