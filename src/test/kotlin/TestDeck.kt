import model.Card
import model.Deck

class TestDeck(private val cards: MutableList<Card>) : Deck {
    override fun pop(): Card {
        return cards.removeAt(0)
    }
}
