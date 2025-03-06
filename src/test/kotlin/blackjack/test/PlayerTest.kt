package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Player(
    val name: String,
)

class PlayerTest {
    @Test
    fun `플레이어는 이름으로 구분된다`() {
        val player = Player(name = "Eden")
        assertThat(player.name).isEqualTo("Eden")
    }
}
