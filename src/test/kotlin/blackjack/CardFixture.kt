package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape

object CardFixture {
    fun createCards(
        count: Int,
        cardNumber: CardNumber,
    ): List<Card> {
        val cardNumbers =
            listOf(
                Card(Shape.CLOVER, cardNumber),
                Card(Shape.SPADE, cardNumber),
                Card(Shape.HEART, cardNumber),
                Card(Shape.DIAMOND, cardNumber),
            )
        return cardNumbers.take(count)
    }

    fun combine(vararg cards: List<Card>): List<Card> = cards.reduce { wholeCards, cardElements -> wholeCards + cardElements }
}
