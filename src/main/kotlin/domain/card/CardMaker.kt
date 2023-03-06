package domain.card

class CardMaker {
    fun makeCards(): List<Card> = Shape.values()
        .map { makeCard(it) }
        .flatten()

    private fun makeCard(shape: Shape): List<Card> = CardValue.values().map { Card(shape, it) }
}
