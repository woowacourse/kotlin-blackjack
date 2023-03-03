package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

class CardGenerator(private val randomGenerator: Generator) {

    fun generateCard(): Card = Card(generateCardNumber(), generateCardShape())

    fun generateCardNumber(): CardNumber = randomGenerator.generateCardNumber()

    fun generateCardShape(): CardShape = randomGenerator.generateCardShape()
}
