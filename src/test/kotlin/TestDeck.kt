import model.card.Card
import model.card.Deck

class TestDeck(private val _cards: MutableList<Card>) : Deck {
    val cards: List<Card>
        get() = _cards.toList()

    override fun pop(): Card {
        return _cards.removeAt(0)
    }
}
