package model.domain.card

import model.tools.CardNumber
import model.tools.Shape

class Card private constructor(
    val shape: Shape,
    val cardNumber: CardNumber,
) {

    companion object {
        private val cardCache: MutableMap<Pair<Shape, CardNumber>, Card> = mutableMapOf()
        fun of(shape: Shape, cardNumber: CardNumber): Card =
            cardCache.getOrPut(shape to cardNumber) { Card(shape, cardNumber) }
    }
}
