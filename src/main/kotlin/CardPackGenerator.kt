class CardPackGenerator {
    fun createCards(): List<Card> {
        return buildList { Rank.values().forEach { rank -> addAll(Suit.values().map { suit -> Card(rank, suit) }) } }
    }
}
