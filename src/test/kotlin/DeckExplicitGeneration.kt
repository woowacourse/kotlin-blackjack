import model.card.Card
import model.card.DeckGeneration

class DeckExplicitGeneration(private val cards: List<Card>) : DeckGeneration {
    override fun generate(): List<Card> {
        return cards.toMutableList()
    }
}
