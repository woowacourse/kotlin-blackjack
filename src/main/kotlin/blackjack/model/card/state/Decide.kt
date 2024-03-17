package blackjack.model.card.state

import blackjack.model.card.CardHand
import blackjack.model.result.Money
import blackjack.model.result.Score

abstract class Decide(val cardHand: CardHand) : CardHandState {
    override fun getCardHands(): CardHand = cardHand

    override fun countCards(): Int = cardHand.hand.size

    override fun getCardHandScore(): Score = cardHand.calculateScore()

    override fun calculateProfit(
        money: Money,
        other: CardHandState,
    ): Money = throw IllegalStateException("현재 상태에서는 수익을 계산할 수 없습니다.")
}
