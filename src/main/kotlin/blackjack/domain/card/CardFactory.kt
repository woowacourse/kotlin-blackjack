package blackjack.domain.card

object CardFactory {
    fun create(): List<Card> =
        CardPattern.entries
            .asSequence()
            .flatMap { pattern ->
                createCardForPattern(pattern)
            }.toList()

    private fun createCardForPattern(pattern: CardPattern): Sequence<Card> =
        CardNumber.entries
            .asSequence()
            .map { number ->
                Card(number, pattern)
            }
}
