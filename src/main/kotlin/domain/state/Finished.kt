package domain.state

import domain.card.Card
import domain.card.Cards
import domain.participant.BettingMoney

abstract class Finished(val hand: Cards, val bettingMoney: BettingMoney) : State {
    override fun bettingMoney(): BettingMoney {
        return bettingMoney
    }

    override fun cards(): Cards {
        return hand
    }

    override fun draw(card: Card): State {
        throw IllegalStateException(DRAW_ERROR)
    }

    override fun stay(): State {
        throw IllegalStateException(STAY_ERROR)
    }

    override fun isFinished(): Boolean {
        return true
    }

    companion object {
        private const val DRAW_ERROR = "더이상 카드를 뽑을 수 없습니다"
        private const val STAY_ERROR = "이미 카드를 더 받을 수 없는 상태입니다"
    }
}
