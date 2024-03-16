package blackjack.model.playing.participants.player

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suit
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드 패에 카드가 2장 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 BLACKJACK 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, Denomination.TEN),
                Card(Suit.SPADE, Denomination.ACE),
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.BLACKJACK)
    }

    @Test
    fun `카드 패의 합이 20 이하이면 카드 패의 상태는 DRAW_POSSIBILITY 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, Denomination.SEVEN),
                Card(Suit.SPADE, Denomination.SIX),
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.DRAW_POSSIBILITY)
    }

    @Test
    fun `카드 패에 카드가 3장 이상 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 DRAW_POSSIBILITY 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, Denomination.FIVE),
                Card(Suit.SPADE, Denomination.SIX),
                Card(Suit.DIAMOND, Denomination.TEN),
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.DRAW_POSSIBILITY)
    }

    @Test
    fun `카드 패 숫자의 합이 21 초과이면 카드 패의 상태는 BUST 이다`() {
        val cardHand =
            CardHand(
                Card(Suit.HEART, Denomination.FIVE),
                Card(Suit.SPADE, Denomination.SIX),
                Card(Suit.DIAMOND, Denomination.SEVEN),
                Card(Suit.DIAMOND, Denomination.EIGHT),
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.BUST)
    }
}
