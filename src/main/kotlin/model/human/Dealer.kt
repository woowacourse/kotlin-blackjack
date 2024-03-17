package model.human

import model.Hand
import model.ResultType
import model.card.Deck

class Dealer(hand: Hand, override val humanInfo: HumanInfo = HumanInfo("딜러")) : Human(hand, humanInfo) {
    override fun isPossible(): Boolean = hand.getPoint().isLessOrEqualTo(16)

    fun play(deck: Deck) {
        while (hand.getPoint().amount < 17) {
            hand.draw(deck.pop())
        }
    }

    fun getCountOfAdditionalDraw(): Int = hand.cards.size - 2

    private fun getCompareResult(other: Human): ResultType {
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

    fun judge(other: Human) {
        when (getCompareResult(other)) {
            ResultType.WIN -> {
                humanInfo.exchangeMoney(other.humanInfo, -1.0)
            }

            ResultType.LOSE -> {
                if (other.isBlackJack()) {
                    humanInfo.exchangeMoney(other.humanInfo, 1.5)
                } else {
                    humanInfo.exchangeMoney(other.humanInfo, 1.0)
                }
            }

            ResultType.DRAW -> {
                humanInfo.exchangeMoney(other.humanInfo, 0.0)
            }
        }
    }
}
