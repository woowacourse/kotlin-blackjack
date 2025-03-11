package model

class CardsGenerator {
    fun generateCards(): Cards {
        val cards = Shape.entries.flatMap { shape ->
            CardRank.entries.map { cardRank ->
                Card(cardRank, shape)
            }
        }.shuffled()
        return Cards(cards)
    }
}
