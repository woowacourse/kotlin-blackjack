package domain.card

class CardsGenerator {

    fun createCards() = mutableListOf<Card>().apply {
        CardShape.values().forEach { shape -> addNumbers(this, shape) }
    }

    private fun addNumbers(deck: MutableList<Card>, shape: CardShape) {
        CardNumber.values().forEach { number -> deck.add(Card(shape, number)) }
    }
}
