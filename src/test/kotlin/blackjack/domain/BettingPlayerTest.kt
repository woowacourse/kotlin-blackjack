package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BettingPlayerTest() {
    @Test
    fun `배팅 금액을 갖는다`() {
        val player = Player("glo")
        val money = 1000

        val bettingPlayer = BettingPlayer(player, money)
        assertThat(bettingPlayer.money).isEqualTo(1000)
    }
}
