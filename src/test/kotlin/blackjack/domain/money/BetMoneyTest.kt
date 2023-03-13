package blackjack.domain.money

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BetMoneyTest {
    @ParameterizedTest(name = "{0}원을 배팅한다.")
    @ValueSource(ints = [1000, 3000, 10000])
    fun `배팅 금액은 1000원 단위의 양수이다`(value: Int) {
        assertDoesNotThrow {
            BetMoney(value)
        }
    }

    @ParameterizedTest(name = "{0}원을 배팅한다.")
    @ValueSource(ints = [-1, 0, -1000, -15000])
    fun `배팅 금액이 양수가 아니면 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> {
            BetMoney(value)
        }
    }

    @ParameterizedTest(name = "{0}원을 배팅한다.")
    @ValueSource(ints = [1001, 1200, 1234, 10200])
    fun `배팅 금액이 1000원 단위가 아니면 예외가 발생한다`(value: Int) {
        assertThrows<IllegalArgumentException> {
            BetMoney(value)
        }
    }

    @ParameterizedTest(name = "{0}원을 반환한다.")
    @CsvSource("1000, 1000", "3000, 3000", "5000, 5000", "10000, 10000")
    fun `배팅 금액이 보유한 금액을 반환한다`(amount: Int, expected: Int) {
        val betMoney = BetMoney(amount)

        assertThat(betMoney.getAmount()).isEqualTo(expected)
    }

    @ParameterizedTest(name = "{0}원에 {1}을 곱하면 {2}이다.")
    @CsvSource(
        "1000, 2.0, 2000",
        "2000, 2.0, 4000",
        "3000, 2.0, 6000",
        "10000, 3.0, 30000",
    )
    fun `배팅 금액을 값을 곱한다`(amount: Int, multiply: Double, expected: Int) {
        val betMoney = BetMoney(amount)

        assertThat((betMoney.times(multiply)).getAmount()).isEqualTo(expected)
    }

    @Test
    fun `배팅 금액을 곱했을 때 값이 음수일 수 없다`() {
        assertThrows<IllegalArgumentException> {
            BetMoney(1000) * -2.0
        }
    }

    @ParameterizedTest(name = "{0}원에 {1}을 곱하면 {2}이다.")
    @CsvSource(
        "1000, 2.0, 2000",
        "2000, 2.0, 4000",
        "3000, 2.0, 6000",
        "10000, 3.0, 30000",
    )
    fun `배팅 금액을 값을 더한다`(amount: Int, multiply: Double, expected: Int) {
        val betMoney = BetMoney(amount)

        assertThat((betMoney.times(multiply)).getAmount()).isEqualTo(expected)
    }
}
