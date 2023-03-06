package blackjack.domain.card

data class Card(val mark: CardMark, val value: CardValue) {
    fun isACE(): Boolean = value == CardValue.ACE
}
