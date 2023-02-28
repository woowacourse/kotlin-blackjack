package blackjack.domain.carddeck.cardnumbergenerator

import blackjack.Shape
import blackjack.domain.CardNumber

class RandomCardNumberGenerator : CardNumberGenerator {
    private val cardNumbersBy: Map<Shape, MutableList<CardNumber>> = Shape.values().associateWith {
        CardNumber.shuffledCardNumbers().toMutableList()
    }

    override fun getCardNumber(shape: Shape): CardNumber? = cardNumbersBy[shape]?.removeFirstOrNull()
}
