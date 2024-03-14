package blackjack.model

class Card private constructor(val pattern: Pattern, val number: CardNumber) {
    companion object {
        private val newCards = mutableSetOf<Card>()

        init {
            Pattern.entries.forEach { pattern ->
                CardNumber.entries.forEach { number ->
                    newCards.add(Card(pattern = pattern, number = number))
                }
            }
        }

        fun createDeck(): List<Card> = newCards.toList()

        fun from(
            pattern: Pattern,
            number: CardNumber,
        ): Card {
            return newCards.first { it.pattern == pattern && it.number == number }
        }

        fun of(value: List<Pair<Pattern, CardNumber>>): List<Card> {
            val newCards = mutableListOf<Card>()
            value.forEach {
                newCards.add(Card(pattern = it.first, number = it.second))
            }

            return newCards
        }
    }
}
