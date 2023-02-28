class CardPackGenerator {
    fun createCards(): Cards {
        return Cards(buildList { Rank.values().forEach { rank -> addAll(Suit.values().map { suit -> Card(rank, suit) }) } })
    }
}
