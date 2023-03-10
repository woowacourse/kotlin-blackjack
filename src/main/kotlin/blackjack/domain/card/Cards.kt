package blackjack.domain.card

class Cards(
    cards: List<Card> = listOf(Card.draw(), Card.draw()),
) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    val isDrawnNothing: Boolean
        get() = cards.size == INITIAL_CARDS_SIZE

    var state: CardsState = CardsState.Running(getTotalCardsScore())
        private set

    init {
        require(cards.size == INITIAL_CARDS_SIZE)

        if (getTotalCardsScore() == BLACKJACK_SCORE) {
            state = CardsState.BlackJack
        }
    }

    fun draw(card: Card = Card.draw()) {
        _cards.add(card)
        updateCardsState()
    }

    private fun updateCardsState() {
        if (getMinimumCardsScore() > BLACKJACK_SCORE) {
            state = CardsState.Bust
            return
        }
        state = CardsState.Running(getTotalCardsScore())
    }

    fun getMinimumCardsScore(): Int = cards.sumOf { card -> card.number.value }

    fun getTotalCardsScore(): Int {
        val minimumScore = getMinimumCardsScore()

        if (cards.any { card -> card.number == CardNumber.A }) {

            return minimumScore + CardNumber.decideAceValue(minimumScore)
        }

        return minimumScore
    }

    companion object {

        private const val INITIAL_CARDS_SIZE = 2
        private const val BLACKJACK_SCORE = 21
    }
}
