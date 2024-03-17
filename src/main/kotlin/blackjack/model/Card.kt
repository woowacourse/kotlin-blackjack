package blackjack.model

class Card private constructor(val pattern: Pattern, val number: CardNumber) {
    companion object {
        private val cards = initDeck()

        private fun initDeck(): List<Card> {
            return Pattern.entries.flatMap { pattern ->
                CardNumber.entries.map { number ->
                    Card(pattern = pattern, number = number)
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
