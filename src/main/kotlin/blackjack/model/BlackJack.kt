package blackjack.model

import blackjack.state.State

class BlackJack {
    private val handCard = HandCard()
    private var state: State = State.Action.Hit

    fun checkDrawState(): Boolean {
        return when (state) {
            is State.Action.Hit -> true
            is State.Finish -> false
        }
    }

    fun getBlackJackState(): State {
        return state
    }

    fun getCards(): Set<Card> {
        return handCard.cards
    }

    fun getHandCardScore(): Int {
        return handCard.getTotalCardsSum()
    }

    fun switchToStayState() {
        state = State.Finish.Stay
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
                state = State.Action.Hit
                applyGameStateWithAceCount(totalScore)
            }

            BLACK_JACK_SCORE -> applyBlackJackStateWithCardCount()
            in BUST_SCORE..MAX_SCORE -> state = State.Finish.Bust
        }
    }

    private fun applyGameStateWithAceCount(totalScore: Int) {
        if (handCard.updateGameStateWithAceCount(totalScore)) {
            state = State.Finish.BlackJack
        }
    }

    private fun applyBlackJackStateWithCardCount() {
        if (handCard.checkBlackJackStateWithCardCount()) {
            state = State.Finish.BlackJack
        }
    }

    companion object {
        const val BLACK_JACK_SCORE: Int = 21
        private const val MIN_SCORE: Int = 0
        private const val BUST_SCORE: Int = 22
        private const val MAX_SCORE: Int = 61
    }
}
