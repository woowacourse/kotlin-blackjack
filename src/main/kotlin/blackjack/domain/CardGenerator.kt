package blackjack.domain

class CardGenerator(private val randomGenerator: Generator) {

    fun generateCardNumber(): CardNumber {
        return randomGenerator.generateCardNumber()
    }
}
