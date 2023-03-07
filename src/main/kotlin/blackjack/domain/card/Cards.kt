package blackjack.domain.card

class Cards(
    cards: List<Card> = listOf(Card.draw(), Card.draw()),
) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    var state: CardsState = CardsState.Running
        private set

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
            currentSum += decideAceCardsScore(currentSum)
        }
        return currentSum
    }

    private fun decideAceCardsScore(currentSum: Int): Int {
        if (currentSum >= CURRENT_SUM_STANDARD) {
            return CardNumber.A.value
        }

        return CardNumber.BIG_A.value
    }

    companion object {
        const val INITIAL_CARDS_SIZE = 2
        private const val CURRENT_SUM_STANDARD = 11
    }
}
