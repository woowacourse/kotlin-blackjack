package blackjack.model

import blackjack.state.State

class BlackJack {
    private val handCard = HandCard()
    private var state: State = State.Action.Hit

    fun isDrawState(): Boolean {
        return state.isDrawState()
    }

    fun getState(): State {
        return state
    }

    fun isBlackJackState(): Boolean {
        return state == State.Finish.BlackJack
    }

    fun getCards(): List<Card> {
        return handCard.getCards()
    }

    fun getHandCardScore(): Int {
        return handCard.getGameScore()
    }

    fun switchToStayState() {
        state = State.Finish.Stay
    }

    fun addCard(card: Card) {
        if (isDrawState()) {
            handCard.addCard(card)
            updateGameStateWithScore()
        }
    }

    private fun updateGameStateWithScore() {
        when (handCard.getGameScore()) {
            in MIN_SCORE until BLACK_JACK_SCORE -> {
                state = State.Action.Hit
            }

            BLACK_JACK_SCORE -> applyBlackJackStateWithCardCount()
            in BUST_SCORE..Int.MAX_VALUE -> state = State.Finish.Bust
        }
    }

    private fun applyBlackJackStateWithCardCount() {
        state = if (handCard.isInitialHandSize()) {
            State.Finish.BlackJack
        } else {
            State.Finish.Stay
        }
    }

    companion object {
        const val BLACK_JACK_SCORE: Int = 21
        private const val MIN_SCORE: Int = 0
        private const val BUST_SCORE: Int = 22
    }
}
