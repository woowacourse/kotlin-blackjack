package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.user.Hand

class GameInformation(val hand: Hand = Hand(), state: GameState = GameState.Running.HIT) {
    private var _state: GameState = state
    val state: GameState
        get() = _state

    fun drawCard(card: Card) {
        hand.drawCard(card)
        judgeState()
    }

    fun changeStateToStay() {
        _state = GameState.Finished.STAY
    }

    private fun judgeState() {
        val point = hand.score.point
        _state =
            when {
                point > BLACKJACK_POINT -> GameState.Finished.BUST
                point == BLACKJACK_POINT && hand.cards.size == BLACKJACK_CARD_SIZE ->
                    GameState.Finished.BLACKJACK

                else -> GameState.Running.HIT
            }
    }

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val BLACKJACK_POINT = 21
    }
}
