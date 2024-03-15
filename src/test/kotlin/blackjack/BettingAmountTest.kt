package blackjack

import blackjack.model.BettingAmount
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BettingAmountTest {
    @ParameterizedTest
    @CsvSource("1000", "5000")
    fun `0원 초과의 배팅금액을 입력했을때 예외 발생하지 않음을 확인`(input: Double) {
        assertDoesNotThrow { BettingAmount(input) }
    }

    @ParameterizedTest
    @CsvSource("-1", "0")
    fun `0원 이하의 배팅금액을 입력했을때 예외가 발생함을 확인`(input: Double) {
        assertThrows<IllegalArgumentException> { BettingAmount(input) }
    }
}
