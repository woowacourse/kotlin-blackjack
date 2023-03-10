package blackjack.domain.result

import blackjack.domain.participants.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackSettingResultUserTest {
    @Test
    fun `이름을 가져올 수 있다`() {
        val blackJackResultUser = BlackJackResultUser(Name("아크"), 1000)
        assertThat(blackJackResultUser.name.toString()).isEqualTo("아크")
    }

    @Test
    fun `수익금액을 가져올 수 있다`() {
        val blackJackResultUser = BlackJackResultUser(Name("아크"), 1000)
        assertThat(blackJackResultUser.revenue).isEqualTo(1000)
    }
}
