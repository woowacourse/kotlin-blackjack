package blackjack.domain.card

import blackjack.domain.Generator

class CardGenerator(private val randomGenerator: Generator) {

    fun generateCard(): Card = Card(generateCardNumber(), generateCardShape())

    fun generateCardNumber(): CardNumber = randomGenerator.generateCardNumber()

    fun generateCardShape(): CardShape = randomGenerator.generateCardShape()
}
