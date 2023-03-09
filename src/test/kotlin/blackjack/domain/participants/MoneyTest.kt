package blackjack.domain.participants

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = [1000, 1000000])
    fun `돈은 1000원 이상이다`(money: Int) {
        assertDoesNotThrow {
            Money(money)
        }
    }
}
