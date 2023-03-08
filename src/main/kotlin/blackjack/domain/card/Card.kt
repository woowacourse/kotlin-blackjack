package blackjack.domain.card

data class Card(val mark: CardMark, val value: CardValue) {
    val isAce: Boolean
        get() = value == CardValue.ACE
}
