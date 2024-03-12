package blackjack.model

import blackjack.state.State

class BlackJack {
    private val handCard = HandCard()
    private var _state: State = State.Action.Hit
    val state: State get() = _state

    fun checkDrawState(): Boolean {
        return when (state) {
            is State.Action.Hit -> true
            is State.Finish -> false
        }
    }

    fun getCards(): Set<Card> {
        return handCard.cards
    }

    fun getHandCardScore(): Int {
        return handCard.getTotalCardsSum()
    }

    fun switchToStayState() {
        _state = State.Finish.Stay
    }

    fun addCard(card: Card) {
        handCard.addCard(card)
        updateGameStateWithScore()
    }

    private fun updateGameStateWithScore() {
        when (val totalScore = handCard.getTotalCardsSum()) {
            in MIN_SCORE until BLACK_JACK_SCORE -> {
                _state = State.Action.Hit
                updateGameStateWithAceCount(totalScore)
            }

            BLACK_JACK_SCORE -> _state = State.Finish.BlackJack
            in BUST_SCORE..MAX_SCORE -> _state = State.Finish.Bust
        }
    }

    private fun updateGameStateWithAceCount(totalScore: Int) {
        if (handCard.getAceCount() > MIN_ACE_COUNT) {
            checkCurrentScore(totalScore + Denomination.TRANSFER_ACE_SCORE)
        }
    }

    private fun checkCurrentScore(currentScore: Int) {
        if (currentScore == BLACK_JACK_SCORE) {
            _state = State.Finish.BlackJack
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE: Int = 21
        private const val MIN_SCORE: Int = 0
        private const val MIN_ACE_COUNT: Int = 0
        private const val BUST_SCORE: Int = 22
        private const val MAX_SCORE: Int = 61
    }
}
