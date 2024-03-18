package blackjack.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingTest {
    @ParameterizedTest
    @ValueSource(ints = [10_001, 10_010, 10_100, 11_000])
    fun `Betting 금액의 단위는 10_000 이다`(amount: Int) {
        assertThrows<IllegalArgumentException> { Betting(amount) }
    }

    @Test
    fun `Betting 의 최소 금액은 0이다`() {
        assertAll(
            { assertThrows<IllegalArgumentException> { Betting(-1) } },
            { assertDoesNotThrow { Betting(0) } },
        )
    }
}
