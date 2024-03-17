package blackjack

import blackjack.model.BettingAmount
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BettingAmountTest {
    @ParameterizedTest
    @CsvSource("1000", "5000")
    fun `올바른 배팅 금액 입력 (0원 초과)`(input: Double) {
        assertDoesNotThrow { BettingAmount(input) }
    }

    @ParameterizedTest
    @CsvSource("-1", "0")
    fun `올바르지 않은 배팅 금액 입력 (0원 이하)`(input: Double) {
        assertThrows<IllegalArgumentException> { BettingAmount(input) }
    }
}
