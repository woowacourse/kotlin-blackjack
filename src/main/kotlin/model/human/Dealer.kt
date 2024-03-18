package model.human

import model.GameResult
import model.Hand
import model.card.Deck

class Dealer(hand: Hand, override val humanInfo: HumanInfo = HumanInfo(DEFAULT_NAME)) : Human(hand, humanInfo) {
    override fun isHittable(): Boolean = hand.getPoint().isLessThan(HIT_CONDITION)

    fun play(deck: Deck) {
        while (hand.getPoint().isLessThan(HIT_CONDITION)) {
            hand.add(deck.pop())
        }
    }

    fun getCountOfAdditionalDraw(): Int = hand.cards.size - DEFAULT_CARD_COUNT

    private fun getCompareResult(other: Human): GameResult {
        return when {
            isBusted() -> getResultWhenBusted(other)
            isBlackJack() -> getResultWhenBlackjack(other)
            !isBusted() -> getResultWhenNotBusted(other)
            else -> throw IllegalStateException(ERROR_UNDEFINED_STATE)
        }
    }

    private fun getResultWhenBusted(other: Human): GameResult {
        return when {
            other.hand.isNotBusted() -> GameResult.LOSE
            else -> GameResult.WIN
        }
    }

    private fun getResultWhenBlackjack(other: Human): GameResult {
        return when {
            other.isBlackJack() -> GameResult.DRAW
            else -> GameResult.WIN
        }
    }

    private fun getResultWhenNotBusted(other: Human): GameResult {
        return when {
            other.isBusted() -> GameResult.WIN
            !other.isBusted() && (this.hand.getPoint() > other.hand.getPoint()) -> GameResult.WIN
            !other.isBusted() && (this.hand.getPoint() == other.hand.getPoint()) -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    fun judge(player: Player) {
        when (getCompareResult(player)) {
            GameResult.WIN -> {
                humanInfo.applyResultToMoney(player.humanInfo, PROFIT_RATE_LOSE)
            }

            GameResult.LOSE -> {
                if (player.isBlackJack()) {
                    humanInfo.applyResultToMoney(player.humanInfo, PROFIT_RATE_BLACKJACK)
                } else {
                    humanInfo.applyResultToMoney(player.humanInfo, PROFIT_RATE_WIN)
                }
            }

            GameResult.DRAW -> {
                humanInfo.applyResultToMoney(player.humanInfo, PROFIT_RATE_DRAW)
            }
        }
    }

    companion object {
        private const val HIT_CONDITION = 17
        private const val DEFAULT_CARD_COUNT = 2

        private const val PROFIT_RATE_LOSE = -1.0
        private const val PROFIT_RATE_DRAW = 0.0
        private const val PROFIT_RATE_WIN = 1.0
        private const val PROFIT_RATE_BLACKJACK = 1.5

        private const val DEFAULT_NAME = "딜러"

        private const val ERROR_UNDEFINED_STATE = "정의되지 않은 상태입니다."
    }
}
