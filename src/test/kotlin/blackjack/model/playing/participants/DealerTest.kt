package blackjack.model.playing.participants

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Suit
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 카드 패에 카드가 2장 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 BLACKJACK 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, CardNumber.JACK),
                Card(Suit.SPADE, CardNumber.ACE),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.BLACKJACK)
    }

    @Test
    fun `딜러의 카드 패 합이 16 이하라면 카드 패의 상태는 HIT 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, CardNumber.SEVEN),
                Card(Suit.SPADE, CardNumber.SIX),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.HIT)
    }

    @Test
    fun `딜러의 카드 패 합이 17 이상 20 이하 라면 카드 패의 상태는 STAY 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, CardNumber.SEVEN),
                Card(Suit.SPADE, CardNumber.SIX),
                Card(Suit.DIAMOND, CardNumber.FIVE),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.STAY)
    }

    @Test
    fun `딜러의 카드 패에 카드가 3장 이상 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 STAY 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, CardNumber.FIVE),
                Card(Suit.SPADE, CardNumber.SIX),
                Card(Suit.DIAMOND, CardNumber.TEN),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.STAY)
    }

    @Test
    fun `카드 패 숫자의 합이 21 초과이면 카드 패의 상태는 BUST 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, CardNumber.FIVE),
                Card(Suit.SPADE, CardNumber.SIX),
                Card(Suit.DIAMOND, CardNumber.SEVEN),
                Card(Suit.DIAMOND, CardNumber.EIGHT),
            )

        val actualState = cardHand.getDealerState()
        assertThat(actualState).isEqualTo(CardHandState.BUST)
    }
}
