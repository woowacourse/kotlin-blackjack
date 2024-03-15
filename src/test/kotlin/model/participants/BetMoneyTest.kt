package model.participants

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetMoneyTest {
    @ParameterizedTest
    @ValueSource(strings = ["야호", "팡태", "제이든"])
    fun `배팅 금액은 숫자여야 합니다`(input: String) {
        assertThatThrownBy {
            BetMoney.fromInput(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(BetMoney.ERROR_INVALID_TYPE)
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "-1", "-1500"])
    fun `배팅 금액은 0보다 큰 정수여야 합니다`(input: String) {
        assertThatThrownBy {
            BetMoney.fromInput(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(BetMoney.ERROR_INVALID_AMOUNT)
    }
}
