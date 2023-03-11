package domain.state.dealerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.state.Bust
import domain.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerHitTest {
    @Test
    fun `Hit 에서 Bust 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.TWO), Card(CardShape.HEART, CardNumber.KING))
        val actual =
            DealerHit(hand)
                .draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit 에서 Stay 상태가 된다`() {
        val hand = Hand(
            Card(CardShape.HEART, CardNumber.TWO),
            Card(CardShape.DIAMOND, CardNumber.THREE),
        )
        val actual =
            DealerHit(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
