package blackjack.model.result

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MoneyTest {
    private val money1 = Money(1_500)
    private val money2 = Money(1_000)

    @Test
    fun `돈 끼리 대소 비교`() {
        assertTrue { money1 > money2 }
    }

    @Test
    fun `돈 끼리 덧셈 연산`() {
        assertThat(money1 + money2).isEqualTo(Money(2_500))
    }

    @Test
    fun `돈 끼리 뺄셈 연산`() {
        assertThat(money1 - money2).isEqualTo(Money(500))
    }

    @Test
    fun `돈 끼리 곱셈 연산`() {
        assertThat(money1 * 1.5).isEqualTo(Money(2_250))
    }

    @Test
    fun `돈의 부호를 바꾼다`() {
        assertThat(-money1).isEqualTo(Money(-1_500))
        assertThat(-money2).isEqualTo(Money(-1_000))
    }
}
