import model.card.Card
import model.card.DeckGenerationStrategy

class DeckExplicitGenerationStrategy(private val cards: List<Card>) : DeckGenerationStrategy {
    override fun generate(): List<Card> {
        return cards.toMutableList()
    }
}
