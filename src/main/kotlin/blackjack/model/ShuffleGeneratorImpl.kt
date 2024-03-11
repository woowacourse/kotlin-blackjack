package blackjack.model

object ShuffleGeneratorImpl : ShuffleGenerator {
    override fun shuffleGameDeck(): List<Card> {
        val newCards = mutableListOf<Card>()

        Pattern.entries.forEach { pattern ->
            CardNumber.entries.forEach { number ->
                newCards.add(Card(pattern = pattern, number = number))
            }
        }
        return newCards.shuffled()
    }

    override fun shuffleGameDeck(cards: List<Card>): List<Card> {
        return cards
    }
}
