package blackjack.model

class Card private constructor(val pattern: Pattern, val number: CardNumber) {
    companion object {
        fun createDeck(): List<Card> {
            val newCards = mutableListOf<Card>()
            Pattern.entries.forEach { pattern ->
                CardNumber.entries.forEach { number ->
                    newCards.add(Card(pattern = pattern, number = number))
                }
            }
            return newCards
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
