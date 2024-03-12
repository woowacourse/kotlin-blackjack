package blackjack.model.card

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    constructor(vararg card: Card) : this(card.toList())

    fun sum(): Int {
        var aceCount = hand.count { it.number == CardNumber.ACE }
        var tempSum = hand.sumOf { it.number.number }
        while (aceCount >= MIN_ACE_COUNT && tempSum > BLACK_JACK) {
            aceCount--
            tempSum -= SUBTRACTION_BETWEEN_MAX_AND_MIN
        }
        return tempSum
    }

    fun addNewCard() {
        _hand.add(CardDeck.getRandomCard())
    }

    override fun toString(): String {
        return "CardHand(hand=$hand)"
    }

    companion object {
        private const val MIN_ACE_COUNT = 1
        private const val BLACK_JACK = 21
        private const val SUBTRACTION_BETWEEN_MAX_AND_MIN = 10
    }
}
