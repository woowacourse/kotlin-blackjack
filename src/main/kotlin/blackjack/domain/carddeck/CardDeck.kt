package blackjack.domain.carddeck

import blackjack.Shape
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.carddeck.cardnumbergenerator.CardNumberGenerator
import blackjack.domain.carddeck.shapegenerator.ShapeGenerator

class CardDeck(private val shapeGenerator: ShapeGenerator, private val cardNumberGenerator: CardNumberGenerator) {
    private lateinit var shape: Shape

    fun drawCard(): Card {
        val cardNumber: CardNumber = getCardNumber()
        return Card(shape, cardNumber)
    }

    private fun changeShape() {
        shape = shapeGenerator.pickShape()
    }

    private fun getCardNumber(): CardNumber {
        changeShape()
        return cardNumberGenerator.getCardNumber(shape) ?: getCardNumber()
    }
}
