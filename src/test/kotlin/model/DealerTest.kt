package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 보유한 카드가 16이하일 경우 게임을 진행할 수 있다`() {
        val cards =
            listOf(
                Card.of(CardRank.FIVE, Shape.CLUB),
                Card.of(CardRank.SIX, Shape.SPADE),
            )

        val dealer = Dealer(Hand(cards))
        Assertions.assertTrue(dealer.decideToHit())
    }

    @Test
    fun `딜러는 보유한 카드가 17이상인 경우 카드를 뽑을 수 없다`() {
        val cards =
            listOf(
                Card.of(CardRank.FIVE, Shape.CLUB),
                Card.of(CardRank.TEN, Shape.SPADE),
                Card.of(CardRank.SIX, Shape.SPADE),
            )

        val dealer = Dealer(Hand(cards))
        Assertions.assertFalse(dealer.decideToHit())
    }
}
