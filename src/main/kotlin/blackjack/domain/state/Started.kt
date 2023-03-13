package blackjack.domain.state

import blackjack.domain.card.Hand

abstract class Started(override val hand: Hand) : State {
    override fun stay(): State {
        throw IllegalStateException(CANT_STAY_ERROR)
    }

    override fun getProfit(): Double = throw IllegalStateException(CANT_CALCULATE_PROFIT)

    companion object {
        private const val CANT_STAY_ERROR = "게임이 진행중일 때만 스테이 상태가 될 수 있습니다."
        private const val CANT_CALCULATE_PROFIT = "게임이 끝나기 전까지는 수익을 계산할 수 없습니다."
    }
}
