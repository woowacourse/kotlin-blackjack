package blackjack.model

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    var sum: Int = sum()
        private set

    constructor(vararg card: Card) : this(card.toList())

    private fun sum(): Int {
        var aceCount = hand.count { it.number == CardNumber.ACE }
        var tempSum = hand.sumOf { it.number.number }
        while (aceCount > 0 && tempSum > 21) {
            aceCount--
            tempSum -= 10
        }
        return tempSum
    }

    fun addNewCard() {
        _hand.add(CardDeck.getRandomCard())
    }
}
