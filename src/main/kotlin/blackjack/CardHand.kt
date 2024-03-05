package blackjack

data class CardHand(private val hand: List<Card>) {
    constructor(vararg card: Card) : this(card.toList())

    fun sum(): Int = hand.sumOf { it.number.number }
}
