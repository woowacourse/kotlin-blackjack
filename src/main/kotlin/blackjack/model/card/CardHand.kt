package blackjack.model.card

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    constructor(vararg card: Card) : this(card.toList())

    fun sum(): Int {
        val numbersSum = numbersSum()
        if (canGetBonusPoint()) return numbersSum + BONUS_POINT
        return numbersSum
    }

    fun addNewCard() {
        _hand.add(CardDeck.getRandomCard())
    }

    fun addNewCard(card: Card) {
        _hand.add(card)
    }

    private fun canGetBonusPoint(): Boolean = hasAce() && (numbersSum() <= MAX_NUMBER_SUM_FOR_BONUS_POINT)

    private fun hasAce(): Boolean = hand.any { card -> card.number == CardNumber.ACE }

    private fun numbersSum(): Int = hand.sumOf { card -> card.number.number }

    override fun toString(): String {
        return "CardHand(hand=$hand)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardHand

        if (hand != other.hand) return false

        return true
    }

    override fun hashCode(): Int {
        return hand.hashCode()
    }

    companion object {
        private const val MAX_NUMBER_SUM_FOR_BONUS_POINT = 11
        private const val BONUS_POINT = 10
    }
}
