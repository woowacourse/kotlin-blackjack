package blackjack.domain.participants

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = [1000, 1000000])
    fun `돈은 1000원으로 나누어떨어져야 한다`(money: Int) {
        assertDoesNotThrow {
            Money(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [999, 0, -1000])
    fun `돈이 1000원 이하인 경우 예외가 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            Money(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [12345, 120])
    fun `돈은 1000원으로 나누어떨어지지 않으면 예외가 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            Money(money)
        }
    }
}
