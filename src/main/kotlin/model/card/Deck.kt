package model.card

class Deck private constructor(private val cards: MutableList<Card>) {
    fun pop(): Card {
        return cards.removeAt(0)
    }

    companion object {
        fun create(deckGeneration: DeckGeneration): Deck {
            return Deck(deckGeneration.generate().toMutableList())
        }
    }
}
