package blackjack.domain

data class Card(val shape: Shape, val cardNumber: CardNumber) {
    init {
        require(shape in Shape.values()) { SHAPE_ERROR }
        require(cardNumber in CardNumber.values()) { NUMBER_ERROR }
    }

    companion object {
        private val CARDS: List<Card> = Shape.values().flatMap { shape ->
            CardNumber.all().map { number -> Card(shape, number) }
        }

        fun getAllCards(): MutableList<Card> = CARDS.toMutableList()

        const val SHAPE_ERROR = "모양은 하트, 다이아, 클로버, 스페이드 중 하나여야 합니다."
        const val NUMBER_ERROR = "숫자는 2부터 10까지, ACE, JACK, QUEEN, KING만 가능합니다."
    }
}
