package blackjack.model.state

import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FinishTest {
    @Test
    fun `카드를 받으면 에러를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE), Card(CardShape.SPADE, Denomination.TEN))
        val card = Card(CardShape.HEART, Denomination.THREE)
        val finish =
            object : Finish(hand) {
                override val profitRate: Float
                    get() = 1f
            }
        org.junit.jupiter.api.assertThrows<IllegalStateException> {
            finish.draw(card)
        }
    }

    @Test
    fun `카드를 받지 않을다고 선언하면, 에러를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE), Card(CardShape.SPADE, Denomination.TEN))
        val finish =
            object : Finish(hand) {
                override val profitRate: Float
                    get() = 1f
            }
        assertThrows<IllegalStateException> {
            finish.stop()
        }
    }
}
