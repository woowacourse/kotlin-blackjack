package domain.participant

import domain.card.Card
import domain.card.Cards
import domain.card.Score
import domain.card.ScoreState
import domain.game.GameResultType

class Player(name: Name, cards: Cards, val bettingMoney: BettingMoney) : Participant(name, cards) {
    override fun showInitCards(): List<Card> {
        return cards.list.take(TAKE_TWO)
    }

    override fun isPossibleDrawCard(): Boolean {
        return !cards.getScore().isBurst()
    }

    private fun getGameResult(dealerScore: Score): GameResultType {
        val playerScoreState: ScoreState = getScore().state
        return GameResultType.valueOf(dealerScore.state, playerScoreState)
    }

    fun getProfit(dealerScore: Score): Int {
        val gameResultType = getGameResult(dealerScore)
        return (bettingMoney.money * gameResultType.profitRate).toInt()
    }

    companion object {
        private const val TAKE_TWO = 2
        private const val GAME_RESULT_ERROR = "[ERROR] 승패 반환 오류가 발생하였습니다!!"
        private const val DRAW_NUMBER = 0
        private val POSITIVE_RANGE = 1..Int.MAX_VALUE
        private val NEGATIVE_RANGE = Int.MIN_VALUE..-1
    }
}
