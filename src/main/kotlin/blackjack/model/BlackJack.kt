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
        if (!checkStayState()) {
            updateGameStateWithScore()
        }
    }

    private fun checkStayState(): Boolean {
        return state == State.Finish.Stay
    }

    private fun updateGameStateWithScore() {
        when (val totalScore = handCard.getTotalCardsSum()) {
            in MIN_SCORE until BLACK_JACK_SCORE -> {
                _state = State.Action.Hit
                applyGameStateWithAceCount(totalScore)
            }

            BLACK_JACK_SCORE -> applyBlackJackStateWithCardCount()
            in BUST_SCORE..MAX_SCORE -> _state = State.Finish.Bust
        }
    }

    private fun applyGameStateWithAceCount(totalScore: Int) {
        if (handCard.updateGameStateWithAceCount(totalScore)) {
            _state = State.Finish.BlackJack
        }
    }

    private fun applyBlackJackStateWithCardCount() {
        if (handCard.checkBlackJackStateWithCardCount()) {
            _state = State.Finish.BlackJack
        }
    }

    companion object {
        const val BLACK_JACK_SCORE: Int = 21
        private const val MIN_SCORE: Int = 0
        private const val BUST_SCORE: Int = 22
        private const val MAX_SCORE: Int = 61
    }
}
