class FalseShuffle : CardShuffler {
    override fun spread(cards: List<Card>): List<Card> {
        return cards
    }
}
