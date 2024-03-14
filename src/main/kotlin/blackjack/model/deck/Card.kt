package blackjack.model.deck

data class Card(val cardNumber: CardNumber, val pattern: Pattern) {
    override fun toString(): String = "${cardNumber.value}${pattern.shape}"
}
