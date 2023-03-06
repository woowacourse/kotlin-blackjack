package domain

data class Card(val cardCategory: CardCategory, val cardNumber: CardNumber) {
    val isAce: Boolean
        get() = cardNumber == CardNumber.ACE
}
