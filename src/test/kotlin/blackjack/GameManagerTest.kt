package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `플레이어에게 카드를 1장 나눠준다`() {
        val player1 = Player("a")
        val player2 = Player("딜러")
        val gameManager = GameManager(listOf(player1, player2))

        gameManager.handOutCard(player1)

        assertThat(player1.cards.size).isEqualTo(1)
    }
}
