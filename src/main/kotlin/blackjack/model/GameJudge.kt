package blackjack.model

import blackjack.model.state.GameStatus

object GameJudge {
    fun judge(hand: Hand): GameStatus = GameStatus.of(hand.score, hand.cards.size)
}
