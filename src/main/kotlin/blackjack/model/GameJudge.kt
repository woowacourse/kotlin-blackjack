package blackjack.model

import blackjack.model.state.GameStatus

object GameJudge {
    fun judge(hand: Hand): GameStatus {
        val score = hand.score
        return GameStatus.of(score, hand.cards.size)
    }
}
