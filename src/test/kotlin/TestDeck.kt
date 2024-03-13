import model.card.Card
import model.card.Deck

class TestDeck(private val cards: MutableList<Card>) : Deck {
    override fun pop(): Card {
        return cards.removeAt(0)
    }
}
