package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가진다`() {
        val name = "name"
        val player = Player(name)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `플레이어는 원하는 금액을 베팅한다`() {
        val player = Player("name")
        val bettingAmount = 1_000
        player.bet(bettingAmount)
        assertThat(player.bettingAmount).isEqualTo(bettingAmount)
    }
}
