package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 카드 패의 상태를 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
            )

        val player = Player("해나", cardHand)

        assertThat(player.getState(true)).isEqualTo(CardHandState.HIT)
    }

    @Test
    fun `상태가 HIT 이면 카드 한 장을 더 뽑는다`() {
        val player =
            Player(
                "해나",
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.TWO),
                ),
            )

        player.runPhase(true)

        assertThat(player.cardHand.hand.size).isEqualTo(3)
    }

    @Test
    fun `상태가 HIT 이 아니면 카드 한 장을 더 뽑는다`() {
        val player =
            Player(
                "해나",
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.TWO),
                ),
            )

        player.runPhase(false)

        assertThat(player.cardHand.hand.size).isEqualTo(2)
    }
}
