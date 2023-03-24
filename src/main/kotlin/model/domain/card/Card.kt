package model.domain.card

import model.tools.CardNumber
import model.tools.Shape

data class Card constructor(
    val shape: Shape,
    val cardNumber: CardNumber,
) {

    companion object {
        private val cardCache: MutableMap<Pair<Shape, CardNumber>, Card> = mutableMapOf()
        val deck = Shape.values().flatMap { shape -> matchCardValueAndShape(shape) }

        private fun matchCardValueAndShape(shape: Shape): List<Card> =
            CardNumber.values().map { cardNumber ->
                of(shape, cardNumber)
            }

        fun of(shape: Shape, cardNumber: CardNumber): Card =
            cardCache.getOrPut(shape to cardNumber) { Card(shape, cardNumber) }
    }
}
