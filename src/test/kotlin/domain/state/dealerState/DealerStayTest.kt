package domain.state.dealerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.playerState.PlayerFirstTurn
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerStayTest {
    @Test
    fun `딜러가 Stay 에서 상대가 블랙잭인 경우 상대 배팅 금액의 영점오배 잃는다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val actual = DealerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val other = PlayerFirstTurn(otherHand)
            .draw(Card(CardShape.DIAMOND, CardNumber.ACE))

        Assertions.assertThat(actual.profit(other, Money(10000))).isEqualTo(Profit(-5000.0))
    }
}
