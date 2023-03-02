package blackjack.domain.player

import blackjack.domain.RandomGenerator
import blackjack.domain.Result
import blackjack.domain.card.CardGenerator

class Participant(
    name: String,
    private val generator: CardGenerator = CardGenerator(RandomGenerator())
) : Player(name) {

    lateinit var result: Result
        private set

    fun generateCard() {
        cards.addCard(generator.generateCard())
    }

    fun isGeneratePossible(): Boolean {
        if (cards.sumCardsNumber() > MAX_SUM_NUMBER) return false
        return true
    }
}
