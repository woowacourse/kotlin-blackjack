package blackjack.model

object ShuffleGeneratorImpl : ShuffleGenerator {
    private val deck: List<Card> = createGameDeck()

    override fun shuffleGameDeck(): List<Card> = deck.shuffled()

    private fun createGameDeck(): List<Card> {
        val newCards = mutableListOf<Card>()
        Pattern.entries.forEach { pattern ->
            CardNumber.entries.forEach { number ->
                newCards.add(Card(pattern = pattern, number = number))
            }
        }
        return newCards
    }

    override fun shuffleGameDeck(cards: List<Card>): List<Card> {
        return cards
    }
}
