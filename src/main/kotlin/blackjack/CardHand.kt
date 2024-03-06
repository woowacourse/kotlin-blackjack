package blackjack

abstract class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    abstract fun getCardHandState(isHit: Boolean): CardHandState

    fun sum(): Int {
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
