package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.state.Hit
import blackjack.model.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 이름이 없을 경우, 딜러라는 이름을 가진다`() {
        val dealer = Dealer()

        val expected = "딜러"

        assertThat(dealer.name).isEqualTo(expected)
    }

    @Test
    fun `딜러는 점수가 16이하면 true를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.TWO), Card(CardShape.HEART, Denomination.TEN))
        val hit = Hit(hand)
        val dealer = Dealer(state = hit)

        val actual = dealer.canDraw()

        val expected = true

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 점수가 16초과면 false를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.NINE), Card(CardShape.HEART, Denomination.TEN))
        val hit = Hit(hand)
        val dealer = Dealer(state = hit)

        val actual = dealer.canDraw()

        val expected = false

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `상태를 업데이트 할수 있다`() {
        val dealer = Dealer()
        val card = Card(CardShape.HEART, Denomination.NINE)
        dealer.receiveCard(card)

        val actual = dealer.state

        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `상태를 Finish로 업데이트 할수 있다`() {
        val dealer = Dealer()
        val card = Card(CardShape.HEART, Denomination.NINE)
        dealer.receiveCard(card)
        dealer.stop()

        val actual = dealer.state

        assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
