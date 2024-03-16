package blackjack.model

class Card private constructor(val pattern: Pattern, val number: CardNumber) {
    companion object {
        private val cards = initDeck()

        private fun initDeck(): List<Card> {
            val newCards = mutableListOf<Card>()

            Pattern.entries.forEach { pattern ->
                CardNumber.entries.forEach { number ->
                    newCards.add(Card(pattern = pattern, number = number))
                }
            }
            return newCards.toList()
        }

        fun createDeck(): List<Card> = cards

        fun Pair<Pattern, CardNumber>.toCard(): Card {
            return cards.first { it.pattern == this.first && it.number == this.second }
        }

        fun of(value: List<Pair<Pattern, CardNumber>>): List<Card> {
            return value.map { it.toCard() }
        }
    }
}
