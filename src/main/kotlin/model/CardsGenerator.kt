package model

class CardsGenerator(private val shuffler: Shuffler = RandomShuffler()) {
    fun generateCards(): Cards {
        val cards: MutableList<Card> = mutableListOf()
        Shape.entries.forEach { shape ->
            CardRank.entries.forEach { rank ->
                cards.add(Card.of(rank, shape))
            }
        }
        return Cards(shuffler.shuffle(cards))
    }
}
