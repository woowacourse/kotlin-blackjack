package blackjack.model

abstract class CardHolder(private val name: String, private val cards: MutableList<Card>) {
    fun calculateSum(): Int = cards.sumOf { it.number.value }

    fun pickCard(card: Card) {
        cards.add(card)
    }
}
