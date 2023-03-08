package blackjack.domain.card

class Cards(
    cards: List<Card> = listOf(Card.draw(), Card.draw()),
) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    private var state: CardsState = CardsState.Running

    val size: Int
        get() = cards.size

    init {
        require(cards.size == INITIAL_CARDS_SIZE)

        if (getTotalCardsScore() == 21) {
            state = CardsState.BlackJack
        }
    }

    fun draw(card: Card = Card.draw()) {
        _cards.add(card)

        initCardsState()
    }

    private fun initCardsState() {
        if (getMinimumCardsScore() > 21) {
            state = CardsState.Burst
        }
    }

    fun getMinimumCardsScore(): Int = cards.sumOf { card -> card.number.value }

    fun getTotalCardsScore(): Int {
        val aceCardsCount = cards.count { card -> card.number == CardNumber.A }
        var currentSum = cards
            .filter { card -> card.number != CardNumber.A }
            .sumOf { card -> card.number.value }

        repeat(aceCardsCount) {
            currentSum += CardNumber.decideAceValue(currentSum)
        }
        return currentSum
    }

    fun checkCardsState(cardsState: CardsState): Boolean = state == cardsState

    companion object {
        const val INITIAL_CARDS_SIZE = 2
    }
}
