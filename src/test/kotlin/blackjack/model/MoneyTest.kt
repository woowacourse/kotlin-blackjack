package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `베팅 가능 최소 금액보다 작은 금액을 입력하면, 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Money.of(0)
        }
    }

    @Test
    fun `Money 클래스끼리 더하였을 때, 올바른 금액을 가진 Money를 반환한다`() {
        val money1 = Money(1000)
        val money2 = Money(2000)
        val actualResult = money1 + money2
        assertThat(actualResult).isEqualTo(Money(3000))
    }

    @Test
    fun `Money에서 다른 Money를 차감했을 때, 올바른 금액을 가진 Money를 반환한다`() {
        val money1 = Money(2000)
        val money2 = Money(2000)
        val actualResult = money1 - money2
        assertThat(actualResult).isEqualTo(Money(0))
    }
}
