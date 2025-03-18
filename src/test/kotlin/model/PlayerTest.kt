package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import util.TestCards

class PlayerTest {
    @Test
    fun `플레이어는 게임을 시작하면 2장의 카드를 갖는다`() {
        val cards =
            listOf(
                TestCards.CLUB_KING,
                TestCards.SPADE_QUEEN,
            )

        assertThat(Hand(cards).getCardsCount()).isEqualTo(2)
    }

    @Test
    fun `플레이어는 카드를 받을지 결정할 수 있다`() {
        val cards =
            listOf(
                TestCards.CLUB_SIX,
                TestCards.SPADE_NINE,
            )

        val player = Player("joy", Hand(cards))
        assertTrue(player.decideToHit())
    }

    @Test
    fun `플레이어가 16 이하일 경우 카드를 뽑을 수 있다`() {
        val cards =
            listOf(
                TestCards.SPADE_FOUR,
                TestCards.CLUB_FIVE,
            )

        val player = Player("joy", Hand(cards))

        assertTrue(player.decideToHit())
    }
}
