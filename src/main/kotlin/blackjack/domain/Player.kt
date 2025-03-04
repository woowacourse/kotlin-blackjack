package blackjack.domain

class Player(
    name: String,
) : Person(name) {
    private var _resultState: ResultState? = null
    val resultState: ResultState get() = _resultState!!

    fun updateResultState(state: ResultState) {
        _resultState = state
    }

    override fun draw(
        deck: Deck,
        amount: Int,
    ) {
        val drawnCards = deck.draw(amount)
        hand.addCard(drawnCards)
    }
}
