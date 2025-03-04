package blackjack.domain

class Card private constructor(
    val number: CardNumber,
    val pattern: CardPattern,
) {
    companion object {
        fun create(
            number: CardNumber,
            pattern: CardPattern,
        ): Card {
            return Card(number, pattern)
        }
    }
}
