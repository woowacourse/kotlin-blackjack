package domain.card

class CardMaker {
    fun makeCards(): List<Card> {
        val cards = mutableListOf<Card>()

        for (shape in Shape.values()) {
            cards.addAll(makeCard(shape))
        }
        return cards.toList().shuffled()
    }

    private fun makeCard(shape: Shape): List<Card> = CardValue.values().map { Card(shape, it) }
}
