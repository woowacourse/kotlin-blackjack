package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.result.Score

abstract class Done(private val cardHand: CardHand) : CardHandState {
    abstract fun earningRate(other: CardHandState): Double

    override fun draw(card: Card): CardHandState = throw IllegalStateException("현재 상태에서는 카드를 뽑을 수 없습니다.")

    override fun stay(): CardHandState = throw IllegalStateException("현재 상태에서는 stay 선언을 할 수 없습니다.")

    override fun getCardHands(): CardHand = cardHand

    override fun countCards(): Int = cardHand.hand.size

    override fun getCardHandScore(): Score = cardHand.calculateScore()

    override fun calculateProfit(
        money: Int,
        other: CardHandState,
    ): Double = earningRate(other) * money
}
