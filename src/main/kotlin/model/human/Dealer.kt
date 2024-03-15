package model.human

import model.Hand
import model.ResultType

class Dealer(hand: Hand, override val humanInfo: HumanInfo = HumanInfo("딜러")) : Human(hand, humanInfo) {
    override fun isPossible(): Boolean = hand.getPoint().isLessOrEqualTo(16)

    fun getCompareResult(other: Human): ResultType {
        return when {
            isBusted() -> getResultWhenBusted(other)
            isBlackJack() -> getResultWhenBlackjack(other)
            !isBusted() -> getResultWhenNotBusted(other)
            else -> throw IllegalStateException("정의되지 않은 상태입니다.")
        }
    }

    private fun getResultWhenBusted(other: Human): ResultType {
        return when {
            other.hand.isNotBusted() -> ResultType.LOSE
            else -> ResultType.WIN
        }
    }

    private fun getResultWhenBlackjack(other: Human): ResultType {
        return when {
            other.isBlackJack() -> ResultType.DRAW
            else -> ResultType.WIN
        }
    }

    private fun getResultWhenNotBusted(other: Human): ResultType {
        return when {
            other.isBusted() -> ResultType.WIN
            !other.isBusted() && (this.hand.getPoint() > other.hand.getPoint()) -> ResultType.WIN
            !other.isBusted() && (this.hand.getPoint() == other.hand.getPoint()) -> ResultType.DRAW
            else -> ResultType.LOSE
        }
    }
}
