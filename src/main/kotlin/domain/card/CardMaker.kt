package domain.card

class CardMaker {
    fun make(): List<Card> {
        val cards = mutableListOf<Card>()

        for (shape in Shape.values()) {
            cards.addAll(makeCard(shape))
        }
        return cards.toList().shuffled()
    }

    private fun makeCard(shape: Shape): List<Card> {
        val result = mutableListOf<Card>()
        VALUES.forEach {
            result.add(Card(shape, it))
        }
        return result.toList()
    }

    companion object {
        private val VALUES = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "K", "J", "Q")
    }
}
