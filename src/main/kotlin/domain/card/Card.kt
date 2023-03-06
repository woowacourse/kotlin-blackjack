package domain.card

class Card(val shape: Shape, val cardNumber: CardNumber) {

    override fun toString(): String {
        return "${cardNumber.cardSign}${shape.pattern}"
    }

    companion object {
        fun valueOf(card: Card): Int {
            return card.cardNumber.number
        }
    }
}
