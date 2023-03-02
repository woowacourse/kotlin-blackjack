package blackjack.domain

class RandomGenerator : Generator {
    override fun generateCardNumber(): CardNumber {
        val cardNumbers: List<CardNumber> = CardNumber.values().map { it }.toList()
        return cardNumbers.shuffled()[0]
    }
}
