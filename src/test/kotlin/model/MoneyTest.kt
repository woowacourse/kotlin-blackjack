package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `돈의 양을 변경할 수 있다`() {
        val money = Money(0)
        money.changeAmount(1000)
        assertThat(money.amount).isEqualTo(1000)
    }

    @Test
    fun `돈객체끼리 더할 수 있다`() {
        val money1 = Money(1000)
        val money2 = Money(1500)
        money1.add(money2)
        assertThat(money1.amount).isEqualTo(2500)
    }

    @Test
    fun `돈 객체에 int 타입으로 금액을 더할 수 있다`() {
        val money1 = Money(1000)
        money1.add(1500)
        assertThat(money1.amount).isEqualTo(2500)
    }
}
