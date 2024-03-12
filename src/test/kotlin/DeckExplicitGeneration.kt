import model.card.*

class DeckExplicitGeneration(private val cards: List<Card>) : DeckGeneration {
    override fun generate(): List<Card> {
        return cards.toMutableList()
    }
}
