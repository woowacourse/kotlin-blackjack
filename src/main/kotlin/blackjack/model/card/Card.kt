package blackjack.model.card

class Card private constructor(val denomination: Denomination, val suite: Suite) {
    fun getScore(): Int {
        return denomination.score
    }

    companion object {
        val bundle: List<Card> = createCardBundle()

        private fun createCardBundle(): List<Card> {
            val denominations = Denomination.entries
            val suites = Suite.entries
            return List(denominations.size * suites.size) {
                Card(denominations[it / suites.size], suites[it % suites.size])
            }
        }

        fun from(cardProvider: CardProvider): Card {
            return cardProvider.provide()
        }

        fun of(
            denomination: Denomination,
            suite: Suite,
        ): Card {
            return bundle.first { it.denomination == denomination && it.suite == suite }
        }
    }
}
