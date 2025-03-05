package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun `딜러와 플레이어 중 카드의 총합이 큰 사람이 이긴다`() {
        val dealer = Dealer(21)
        val player = Player()
        assertThat(dealer.getPlayerResult(player)).isEqualTo(Result.WIN)
    }
}
