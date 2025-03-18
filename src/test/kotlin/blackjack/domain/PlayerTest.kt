package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가진다`() {
        val name = "name"
        val bettingAmount = Money(1000)
        val player = Player(name, bettingAmount)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    fun `플레이어는 배팅금을 가진다`() {
        val name = "name"
        val bettingAmount = Money(1000)
        val player = Player(name, bettingAmount)
        assertThat(player.bettingAmount.amount).isEqualTo(1_000)
    }
}
