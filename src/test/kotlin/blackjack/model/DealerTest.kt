package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 카드 패 상태를 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
            )

        val dealer = Dealer(cardHand)

        assertThat(dealer.getState()).isEqualTo(CardHandState.HIT)
    }

    @Test
    fun `상태가 HIT 이면 카드 한 장을 더 뽑는다`() {
        val dealer =
            Dealer(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.TWO),
                ),
            )

        if (dealer.getState() == CardHandState.HIT) {
            dealer.runPhase()
        }

        assertThat(dealer.cardHand.hand.size).isEqualTo(3)
    }

    @Test
    fun `상태가 HIT 이 아니면 카드를 더 뽑지 않는다`() {
        val dealer =
            Dealer(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.HEART, CardNumber.NINE),
                ),
            )

        if (dealer.getState() == CardHandState.HIT) {
            dealer.runPhase()
        }

        assertThat(dealer.cardHand.hand.size).isEqualTo(2)
    }
}
