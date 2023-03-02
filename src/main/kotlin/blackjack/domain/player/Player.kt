package blackjack.domain.player

import blackjack.domain.RandomGenerator
import blackjack.domain.card.Card
import blackjack.domain.card.CardGenerator
import blackjack.domain.card.Cards

open class Player(
    val name: String,
    private val generator: CardGenerator = CardGenerator(
        RandomGenerator()
    )
) {

    val cards: Cards = Cards()

    init {
        require(name.length in 2..10) { ERROR_NAME_LENGTH }
    }

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun generateCard() {
        cards.addCard(generator.generateCard())
    }

    companion object {
        const val MAX_SUM_NUMBER = 21

        const val ERROR_NAME_LENGTH = "이름은 2글자 이상 10글자 이하여야 합니다."
    }
}
