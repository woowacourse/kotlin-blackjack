package blackjack.model.state

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.card.Hand

class StartGame(
    override val hand: Hand = Hand(emptyList()),
) : State {
    override fun draw(card: Card): Hit {
        val newHand = hand + card
        return Hit(newHand)
    }

    override fun profit(): Float = throw IllegalStateException("게임이 끝나야지 수익률을 구할 수 있습니다.")

    override fun expectedProfit(money: Money): Money = throw IllegalStateException("게임이 끝나야지 수익률을 구할 수 있습니다.")

    override fun stop(): State = Stay(hand)
}
