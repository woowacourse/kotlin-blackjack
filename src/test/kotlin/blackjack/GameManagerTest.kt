package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `플레이어에게 카드를 1장 나눠준다`() {
        // given
        val player1 = Player("a")
        val gameManager = GameManager(listOf(player1))

        // when
        gameManager.handOutCard(player1)

        // then
        assertThat(player1.cards.size).isEqualTo(1)
    }

    @Test
    fun `초기 세팅은 플레이어와 딜러에게 카드 2장씩 나눠준다 `() {
        // given
        val player1 = Player("a")
        val dealer = Player("딜러")
        val gameManager = GameManager(listOf(player1, dealer))

        // when
        gameManager.setting()

        // then
        assertThat(player1.cards.size).isEqualTo(2)
        assertThat(dealer.cards.size).isEqualTo(2)
    }
}
