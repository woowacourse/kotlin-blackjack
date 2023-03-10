package blackjack.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
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
}
