package blackjack

abstract class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    abstract fun getCardHandState(isHit: Boolean): CardHandState

    protected fun sum(): Int = hand.sumOf { it.number.number }

    fun addNewCard() {
        _hand.add(CardDeck.getRandomCard())
    }
}
