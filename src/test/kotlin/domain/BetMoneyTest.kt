package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class BetMoneyTest {
    @Test
    fun `투자 가능 금액은 천원에서 천만원 사이여야 한다`() {
        assertAll(
            "투자 가능 금액 범위를 벗어나면 예외발생",
            {
                assertThrows<IllegalStateException> { BetMoney(100) }
            },
            {
                assertThrows<IllegalStateException> { BetMoney(1000_0001) }
            },
            {
                assertThrows<IllegalStateException> { BetMoney(-1) }
            },
        )
    }
}
