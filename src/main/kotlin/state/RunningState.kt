package state

import domain.card.Card
import domain.card.Cards

abstract class RunningState(cards: Cards) : StartedState(cards) {
    override val isFinished: Boolean
        get() = false

    override val resultScore: Int
        get() {
            throw IllegalStateException(ERROR_GAME_IS_NOT_OVER)
        }

    override val rateOfProfit: Double
        get() {
            throw IllegalStateException(ERROR_GAME_IS_NOT_OVER)
        }

    override fun draw(card: Card): State {
        val nextCards = cards.add2(card)
        return next(nextCards)
    }

    override fun resultProfit(other: State): Double {
        throw IllegalStateException(ERROR_PROFIT)
    }

    companion object {
        private const val ERROR_GAME_IS_NOT_OVER = "[ERROR] 게임이 아직 끝나지 않았습니다."
        private const val ERROR_PROFIT = "[ERROR] 게임이 아직 끝나지 않아서 수익률을 계산할 수 없습니다."
    }
}
