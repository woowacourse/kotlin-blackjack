package blackjack

import blackjack.model.BettingAmount
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingAmountTest {
    @ParameterizedTest
    @ValueSource(strings = ["-1", "-1000", "900"])
    fun `베팅 금액은 1000원 보다 작은 경우 예외가 발생한다`(invalidAmount: String) {
        assertThrows<IllegalArgumentException> {
            BettingAmount.bettingAmountOf(invalidAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["ㅁ", "a", "@@"])
    fun `베팅 금액이 숫자가 아닌 경우 예외가 발생한다`(invalidAmount: String) {
        assertThrows<IllegalArgumentException> {
            BettingAmount.bettingAmountOf(invalidAmount)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1000", "10000", "9000"])
    fun `베팅 금액이 숫자이고 1000원 이상인 경우 예외가 발생하지 않는다`(validAmount: String) {
        assertDoesNotThrow {
            BettingAmount.bettingAmountOf(validAmount)
        }
    }
}
