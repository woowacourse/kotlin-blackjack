package domain.money

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {
    @ValueSource(ints = [-1000, 9999, 100001, 0, 200000])
    @ParameterizedTest
    fun `배팅 금액은 10000원 보다 작거나 100000원 보다 클 수 없다`(value: Int) {
        assertThrows<IllegalArgumentException> { Money(value) }
    }

    @ValueSource(ints = [10000, 50000, 100000])
    @ParameterizedTest
    fun `배팅 금액은 10000원 과 100000원 사이여야 한다`(value: Int) {
        assertDoesNotThrow { Money(value) }
    }
}
