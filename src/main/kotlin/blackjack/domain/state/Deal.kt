package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Money

class Deal(override val hand: Hand, override val bettingMoney: Money?) : Started(hand) {
    override fun betting(money: Money): State = throw IllegalStateException(ALREADY_BET_ERROR)

    override fun draw(card: Card): State {
        val nextHand = hand + card
        return if (nextHand.size < Running.MIN_HAND_SIZE) Deal(nextHand, bettingMoney) else Hit(nextHand, bettingMoney)
    }

    companion object {
        private const val ALREADY_BET_ERROR = "이미 베팅을 했습니다."
    }
}
