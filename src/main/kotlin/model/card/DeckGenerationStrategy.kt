package model.card

interface DeckGenerationStrategy {
    fun generate(): List<Card>
}
