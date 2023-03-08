package domain.card

class CardMaker {
    fun makeShuffledCards(): List<Card> {
        return Shape.values().flatMap {
            makeCardWithValues(it)
        }.shuffled()
    }

    private fun makeCardWithValues(shape: Shape): List<Card> {
        return CardValue.values().map {
            Card(shape, it)
        }
    }
}
