package blackjack.domain.participants

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 1_000_000])
    fun `배팅 금액은 10원 ~ 1,000,000원만 가능하다`(money: Int) {
        assertDoesNotThrow { Money(money) }
    }

    @ParameterizedTest
    @ValueSource(ints = [9, 1_000_001])
    fun `배팅 금액은 10원 ~ 1,000,000원 이외는 에러가 발생된다`(money: Int) {
        assertThrows<IllegalArgumentException> { Money(money) }
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 50_000, 1_000_000])
    fun `금액을 반환한다`(int: Int) {
        val money = Money(int)
        assertThat(money.toInt()).isEqualTo(int)
    }
}
