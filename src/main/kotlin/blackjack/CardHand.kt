package blackjack

class CardHand(hand: List<Card>) {
    private val _hand = hand.toMutableList()
    val hand: List<Card> get() = _hand.toList()

    constructor(vararg card: Card) : this(card.toMutableList())

    fun sum(): Int = hand.sumOf { it.number.number }

    fun addNewCard() {
        _hand.add(CardDeck.getRandomCard())
    }
}
