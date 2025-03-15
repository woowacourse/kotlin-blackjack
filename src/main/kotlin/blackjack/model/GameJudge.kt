package blackjack.model

import blackjack.model.card.Card
import blackjack.model.state.GameStatus

object GameJudge {
    fun judge(cards: List<Card>): GameStatus {
        val score = ScoreCalculator.calculateOptimalSum(cards)
        return GameStatus.of(score, cards.size)
    }
}
