package blackjack.model.card

object Deck {
    val cards: MutableList<Card> = mutableListOf()

    fun initialize() {
        cards.clear()
        for (suit in Suit.entries) {
            for (denomination in Denomination.entries) {
                cards.add(Card(denomination, suit))
            }
        }
        cards.shuffle()
    }

    fun dealCard(): Card = cards.removeAt(0)
}
