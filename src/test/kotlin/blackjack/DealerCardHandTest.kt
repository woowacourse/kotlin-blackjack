package blackjack

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerCardHandTest {
    @Test
    fun `카드 패가 21 초과이면 BURST 패가 터진다`() {
        val dealerCardHand =
            DealerCardHand(
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.HEART, CardNumber.TEN),
            )
        val actual = dealerCardHand.getCardHandState(true)

        Assertions.assertThat(actual).isEqualTo(CardHandState.BURST)
    }

    @Test
    fun `카드 패가 21 이면 Black Jack 이 된다`() {
        val dealerCardHand =
            DealerCardHand(
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.HEART, CardNumber.EIGHT),
            )

        val actual = dealerCardHand.getCardHandState(true)

        Assertions.assertThat(actual).isEqualTo(CardHandState.BLACKJACK)
    }

    @Test
    fun `카드 패가 16 초과일 때 STAY 가 된다`() {
        val dealerCardHand =
            DealerCardHand(
                Card(CardShape.SPADE, CardNumber.EIGHT),
                Card(CardShape.HEART, CardNumber.NINE),
            )

        val actual = dealerCardHand.getCardHandState(true)

        Assertions.assertThat(actual).isEqualTo(CardHandState.STAY)
    }

    @Test
    fun `카드 패가 16 이하일 때 HIT 가 된다`() {
        val dealerCardHand =
            DealerCardHand(
                Card(CardShape.SPADE, CardNumber.EIGHT),
                Card(CardShape.HEART, CardNumber.SEVEN),
            )

        val actual = dealerCardHand.getCardHandState(true)

        Assertions.assertThat(actual).isEqualTo(CardHandState.HIT)
    }
}
