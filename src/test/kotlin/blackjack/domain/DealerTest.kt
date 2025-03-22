package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 딜러라는 이름을 가진다`() {
        val dealer = Dealer()
        assertThat(dealer.name).isEqualTo("딜러")
    }
}
