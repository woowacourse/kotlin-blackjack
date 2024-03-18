package blackjack.model.playing.participants.player

import blackjack.model.DIAMOND_EIGHT
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_SEVEN
import blackjack.model.HEART_TEN
import blackjack.model.SPADE_ACE
import blackjack.model.SPADE_SIX
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.winning.Betting
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
        val player = Player(PlayerName("해나"), cardHand, Betting(0))
        val actualState = cardHand.getState(player)
        assertThat(actualState).isEqualTo(CardHandState.BLACKJACK)
    }

    @Test
    fun `카드 패의 합이 20 이하이고 HIT를 선택했다면 카드 패의 상태는 HIT 이다`() {
        val cardHand =
            CardHand(
                HEART_SEVEN,
                SPADE_SIX,
            )
        val player = Player(PlayerName("해나"), cardHand, Betting(0))
        player.cardHand.hitState = true
        val actualState = cardHand.getState(player)
        assertThat(actualState).isEqualTo(CardHandState.HIT)
    }

    @Test
    fun `카드 패에 카드가 3장 이상 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 STAY 이다`() {
        val cardHand =
            CardHand(
                HEART_FIVE,
                SPADE_SIX,
                HEART_TEN,
            )
        val player = Player(PlayerName("해나"), cardHand, Betting(0))
        val actualState = cardHand.getState(player)
        assertThat(actualState).isEqualTo(CardHandState.STAY)
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
        val player = Player(PlayerName("해나"), cardHand, Betting(0))
        val actualState = cardHand.getState(player)
        assertThat(actualState).isEqualTo(CardHandState.BUST)
    }
}
