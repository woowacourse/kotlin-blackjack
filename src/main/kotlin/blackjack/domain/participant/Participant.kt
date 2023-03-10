package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Participant(val name: String) {
    val cards = Cards()

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun canDraw(): Boolean

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalScore(): Int = cards.calculateTotalScore()

    fun getCards(): List<Card> = cards.items

    fun isBlackjack(): Boolean = cards.isBlackJack()

    fun isBust(): Boolean = cards.isBust()
}
