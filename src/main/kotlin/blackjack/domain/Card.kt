package blackjack.domain

data class Card(val mark: CardMark, val value: CardValue) {

    companion object {
        private val CARDS: List<Card> =
            CardMark.values().map { mark ->
                CardValue.values().map { value ->
                    Card(mark, value)
                }
            }.flatten()
        fun all(): List<Card> = CARDS
    }
}
