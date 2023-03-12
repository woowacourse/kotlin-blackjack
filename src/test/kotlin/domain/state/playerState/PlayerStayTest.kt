package domain.state.playerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.dealerState.DealerFirstTurn
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerStayTest {
    @Test
    fun `플레이어가 Stay 에서 상대가 블랙잭인 경우 수익의 1배 잃는다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN)).stay()

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val other = DealerFirstTurn(otherHand)
            .draw(Card(CardShape.DIAMOND, CardNumber.ACE))

        Assertions.assertThat(actual.profit(other, Money(10000))).isEqualTo(Profit(-10000.0))
    }
}
