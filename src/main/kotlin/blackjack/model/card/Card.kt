package blackjack.model.card

data class Card private constructor(val denomination: Denomination, val suite: Suite) {
    init {
        validateCardInfo(denomination, suite)
    }

    fun getScore(): Int {
        return denomination.score
    }

    private fun validateCardInfo(
        denomination: Denomination,
        suite: Suite,
    ) {
        require(Denomination.entries.contains(denomination)) { INVALID_DENOMINATION }
        require(Suite.entries.contains(suite)) { INVALID_SUITE }
    }

    companion object {
        private const val INVALID_DENOMINATION = "유효하지 않은 카드의 숫자 혹은 알파벳입니다."
        private const val INVALID_SUITE = "유효하지 않은 카드의 모양입니다."
        private val bundle: List<Card> = createCardBundle()

        private fun createCardBundle(): List<Card> {
            val denominations = Denomination.entries
            val suites = Suite.entries
            return List(denominations.size * suites.size) {
                Card(denominations[it / suites.size], suites[it % suites.size])
            }
        }

        fun from(cardProvider: CardProvider): Card {
            return cardProvider.provide(bundle)
        }

        fun of(
            denomination: Denomination,
            suite: Suite,
        ): Card {
            return bundle.first { it.denomination == denomination && it.suite == suite }
        }
    }
}
