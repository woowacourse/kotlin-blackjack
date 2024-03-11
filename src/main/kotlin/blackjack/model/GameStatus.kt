package blackjack.model

class GameStatus(
    hand: Hand = Hand(),
    state: UserState = UserState.RUNNING,
) {
    private var _hand: Hand = hand
    val hand: Hand
        get() = _hand

    private var _state: UserState = state
    val state: UserState
        get() = _state

    fun getCard(card: Card) {
        _hand += card
        updateState()
    }

    private fun updateState() {
        _state =
            when (hand.calculate()) {
                in RUNNING_RANGE -> UserState.RUNNING
                Hand.BLACKJACK_NUMBER -> if (hand.cards.size == MIN_CARD_COUNTS) UserState.BLACKJACK else UserState.STAY
                else -> UserState.BUST
            }
    }

    fun changeState(userState: UserState) {
        _state = userState
    }

    companion object {
        const val MIN_CARD_COUNTS = 2
        val RUNNING_RANGE = (0..20)
    }
}
