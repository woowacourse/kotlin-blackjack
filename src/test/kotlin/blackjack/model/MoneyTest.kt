package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `베팅 금액은 베팅한 금액을 가진다`() {
        val money = Money(100_000.0)

        val actual = money.value

        val expected = 100_000.0

        assertThat(actual).isEqualTo(expected)
    }
}
