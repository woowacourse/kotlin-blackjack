package domain.state

import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.result.Score
import domain.state.playerState.PlayerStay

abstract class Started(protected val hand: Hand) : State {
    override fun getHandCards() = hand.value

    override fun profit(other: State, money: Money): Profit {
        throw IllegalStateException(CANT_PROFIT_ERROR)
    }

    override fun getScore() = Score.of(hand)

    override fun stay(): State {
        return PlayerStay(hand)
    }

    companion object {
        const val CANT_PROFIT_ERROR = "게임 중엔 수익을 계산할 수 없습니다."
    }
}
