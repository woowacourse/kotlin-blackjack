package blackjack.domain

class RandomGenerator : Generator {
    override fun generateCardNumber(): CardNumber {
        val cardNumbers: List<CardNumber> = CardNumber.values().toList()
        return cardNumbers.shuffled()[0]
    }

    override fun generateCardShape(): CardShape {
        val cardShapes: List<CardShape> = CardShape.values().toList()
        return cardShapes.shuffled()[0]
    }
}
