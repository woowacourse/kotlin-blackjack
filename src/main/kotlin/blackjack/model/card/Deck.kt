package blackjack.model.card

class Deck {
    var cards: List<Card>

    init {
        val allCards = mutableListOf<Card>()
        for (suit in Suit.entries) {
            for (denomination in Denomination.entries) {
                allCards.add(Card(denomination, suit))
            }
        }
        cards = allCards.shuffled()
    }

    fun dealCard(): Card {
        val card = cards.first()
        cards = cards.drop(1)
        return card
    }
}
