package model

import model.CardsTest.Companion.cardOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 게임을 시작하면 2장의 카드를 갖는다`() {
        val cards =
            cardOf(
                Card(CardRank.ACE, Shape.CLUB),
                Card(CardRank.SIX, Shape.CLUB),
            )

        assertThat(cards.totalCount).isEqualTo(2)
    }

    @Test
    fun `딜러는 보유한 카드가 16이하일 경우 게임을 진행할 수 있다`() {
        val cards =
            cardOf(
                Card(CardRank.FIVE, Shape.CLUB),
                Card(CardRank.SIX, Shape.SPADE),
            )

        val dealer = Dealer(cards)
        Assertions.assertTrue(dealer.canHit())
    }

    @Test
    fun `딜러는 보유한 카드가 16이하인 경우 카드를 뽑아야한다`() {
        val cards =
            cardOf(
                Card(CardRank.FIVE, Shape.CLUB),
                Card(CardRank.SIX, Shape.SPADE),
            )

        val dealer = Dealer(cards)
        val drawnCard = Card(CardRank.TWO, Shape.SPADE)
        Assertions.assertTrue(dealer.turn(drawnCard))
    }

    @Test
    fun `딜러는 보유한 카드가 16이하인 경우 카드를 1번 이상 뽑아야 한다`() {
        val cards =
            cardOf(
                Card(CardRank.TWO, Shape.CLUB),
                Card(CardRank.THREE, Shape.SPADE),
            )
        val dealer = Dealer(cards)
        val drawnCard = Card(CardRank.TEN, Shape.SPADE)
        val drawCount = dealer.drawCount(drawnCard)

        assertThat(drawCount).isEqualTo(2)
    }
}
