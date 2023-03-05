package domain.card

class CardMaker { // object
    fun makeCards(): List<Card> = Shape.values()
        .map { makeCard(it) }
        .flatten()
        .shuffled()

    private fun makeCard(shape: Shape): List<Card> = CardValue.values().map { Card(shape, it) }
}
