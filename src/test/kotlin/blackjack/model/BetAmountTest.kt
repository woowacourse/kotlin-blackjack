package blackjack.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.assertj.core.api.Assertions.assertThat


class BetAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -1, -999])
    fun `배팅 금액이 0원 이하면 예외를 던진다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            BetAmount(amount)
        }
    }

    @Test
    fun `배팅금액은 Double을 반환할 수 있다`() {
        val amount = BetAmount(100)
        val expect = 100.0

        val actual = amount.toDouble()

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `배팅금액에 *로 곱셈연산을 할 수 있다`() {
        val amount = BetAmount(100)
        val expect = 150.0

        val actual = amount * 1.5

        assertThat(actual).isEqualTo(expect)
    }
}