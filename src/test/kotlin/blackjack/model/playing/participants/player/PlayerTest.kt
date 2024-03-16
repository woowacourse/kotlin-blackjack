package blackjack.model.playing.participants.player

import blackjack.model.DIAMOND_EIGHT
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_SEVEN
import blackjack.model.HEART_TEN
import blackjack.model.SPADE_ACE
import blackjack.model.SPADE_SIX
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드 패에 카드가 2장 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 BLACKJACK 이다`() {
        val cardHand =
            CardHand(
                HEART_TEN,
                SPADE_ACE,
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.BLACKJACK)
    }

    @Test
    fun `카드 패의 합이 20 이하이면 카드 패의 상태는 DRAW_POSSIBILITY 이다`() {
        val cardHand =
            CardHand(
                HEART_SEVEN,
                SPADE_SIX,
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.DRAW_POSSIBILITY)
    }

    @Test
    fun `카드 패에 카드가 3장 이상 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 DRAW_POSSIBILITY 이다`() {
        val cardHand =
            CardHand(
                HEART_FIVE,
                SPADE_SIX,
                HEART_TEN,
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.DRAW_POSSIBILITY)
    }

    @Test
    fun `카드 패 숫자의 합이 21 초과이면 카드 패의 상태는 BUST 이다`() {
        val cardHand =
            CardHand(
                HEART_FIVE,
                SPADE_SIX,
                HEART_SEVEN,
                DIAMOND_EIGHT,
            )

        val actualState = cardHand.getPlayerState()
        assertThat(actualState).isEqualTo(CardHandState.BUST)
    }
}
