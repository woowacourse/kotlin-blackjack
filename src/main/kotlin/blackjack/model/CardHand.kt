package blackjack.model

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    constructor(vararg card: Card) : this(card.toList())

    fun sum(): Int {
        var aceCount = hand.count { it.number == CardNumber.ACE }
        var tempSum = hand.sumOf { it.number.number }
        while (aceCount >= MIN_ACE_COUNT && tempSum > CardHandState.BLACKJACK.precondition) {
            aceCount--
            tempSum -= SUBTRACTION_BETWEEN_MAX_AND_MIN
        }
        return tempSum
    }

    fun addNewCard(cardGenerator: CardGenerator) {
        _hand.add(cardGenerator.draw())
    }

    override fun toString(): String {
        return "CardHand(hand=$hand)"
    }

    companion object {
        private const val MIN_ACE_COUNT = 1
        private const val SUBTRACTION_BETWEEN_MAX_AND_MIN = 10
    }
}
