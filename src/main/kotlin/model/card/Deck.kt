package model.card

class Deck private constructor(private val cards: MutableList<Card>) {
    fun pop(): Card {
        if(cards.isEmpty()) cards.addAll(DeckRandomGenerationStrategy().generate())
        return cards.removeAt(0)
    }

    companion object {
        fun create(deckGenerationStrategy: DeckGenerationStrategy): Deck {
            return Deck(deckGenerationStrategy.generate().toMutableList())
        }
    }
}
