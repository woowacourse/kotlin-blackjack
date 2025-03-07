package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음에 카드를 2장 받는다`() {
        val player = Player()

        assertThat(player.showCards()).hasSize(2)
    }

    @Test
    fun `플레이어는 덱에서 카드를 뽑을 수 있다`() {
        val player = Player()

        player.drawCard()

        assertThat(player.showCards()).hasSize(3)
    }
}
