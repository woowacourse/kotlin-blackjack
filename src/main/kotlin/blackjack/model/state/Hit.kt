package blackjack.model.state

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.card.Hand

class Hit(
    override val hand: Hand,
) : State {
    override fun expectedProfit(money: Money): Money = throw IllegalStateException("게임이 끝나야지 수익률을 구할 수 있습니다.")

    override fun profit(): Float = throw IllegalStateException("게임이 끝나야지 수익률을 구할 수 있습니다.")

    override fun draw(card: Card): State {
        val newHand = hand + card
        val score = newHand.score()
        return when {
            score < BLACKJACK_SCORE -> Hit(newHand)
            newHand.size == FIRST_CARD_COUNT && score == BLACKJACK_SCORE -> Blackjack(newHand)
            score > BLACKJACK_SCORE -> Bust(newHand)
            else -> Stay(newHand)
        }
    }

    override fun stop(): State = Stay(hand)

    companion object {
        const val FIRST_CARD_COUNT = 2
        const val BLACKJACK_SCORE = 21
    }
}
