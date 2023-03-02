package blackjack.domain

class CardGenerator(private val randomGenerator: Generator) {

    fun generateCardNumber(): CardNumber = randomGenerator.generateCardNumber()

    fun generateCardShape(): CardShape = randomGenerator.generateCardShape()
}
