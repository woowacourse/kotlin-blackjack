package domain.card

class CardMaker {
    fun makeShuffledCards(): List<Card> {
        val cards = Shape.values().flatMap {
            makeCardWithValues(it)
        }
        return cards.shuffled()
    }

    private fun makeCardWithValues(shape: Shape): List<Card> {
        val result = CardValue.values().map {
            Card(shape, it)
        }
        return result
    }
}
