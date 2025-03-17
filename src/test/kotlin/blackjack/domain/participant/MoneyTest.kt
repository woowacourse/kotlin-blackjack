package blackjack.domain.participant

import blackjack.model.participant.Money
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `두 Money 객체를 더하면 값이 더해진다`() {
        // given
        val money1 = Money(1000.0)
        val money2 = Money(2000.0)

        // when
        val result = money1 + money2

        // then
        assertEquals(Money(3000.0), result)
    }

    @Test
    fun `두 Money 객체를 빼면 값이 감소한다`() {
        // given
        val money1 = Money(5000.0)
        val money2 = Money(2000.0)

        // when
        val result = money1 - money2

        // then
        assertEquals(Money(3000.0), result)
    }

    @Test
    fun `Money 객체를 숫자와 곱하면 값이 증가한다`() {
        // given
        val money = Money(1500.0)
        val multiplier = 2.0

        // when
        val result = money * multiplier

        // then
        assertEquals(Money(3000.0), result)
    }

    @Test
    fun `Money 객체에 - 기호를 붙이면 음수가 된다`() {
        // given
        val money = Money(2500.0)

        // when
        val result = -money

        // then
        assertEquals(Money(-2500.0), result)
    }

    @Test
    fun `Money ZERO는 0원이다`() {
        // then
        assertEquals(0.0, Money.ZERO.value)
    }
}
