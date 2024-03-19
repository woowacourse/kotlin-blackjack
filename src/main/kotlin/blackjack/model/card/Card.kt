package blackjack.model.card

class Card private constructor(val denomination: Denomination, val suite: Suite) {
    fun score() = denomination.score

    fun isAce() = denomination.isAce()

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

        fun provideCards(
            cardProvider: CardProvider,
            cardCount: Int = 1,
        ): List<Card> {
            return cardProvider.provide(bundle, cardCount)
        }

        fun of(
            denominationValue: String,
            suiteValue: String,
        ): Card {
            val denomination = Denomination.from(denominationValue)
            val suite = Suite.from(suiteValue)

            requireNotNull(denomination) { INVALID_DENOMINATION }
            requireNotNull(suite) { INVALID_SUITE }
            return findCardFromBundle(denomination, suite)
        }

        private fun findCardFromBundle(
            denomination: Denomination,
            suite: Suite,
        ): Card {
            return bundle.first { it.denomination == denomination && it.suite == suite }
        }
    }
}
