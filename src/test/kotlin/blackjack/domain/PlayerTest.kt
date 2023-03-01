package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 갖는다`() {
        val player = Player("pobi")
        assertThat(player.name).isEqualTo("pobi")
    }
}
