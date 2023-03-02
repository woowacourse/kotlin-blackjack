package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

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
