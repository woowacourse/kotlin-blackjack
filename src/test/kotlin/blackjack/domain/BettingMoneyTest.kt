package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class BettingMoneyTest {
    @Test
    fun `배팅금액은 0원보다 커야한다`() {
        assertDoesNotThrow { BettingMoney(1000) }
    }

    @Test
    fun `베팅금액이 0원보다 작으면 에러가 난다`() {
        assertThrows<IllegalArgumentException> { BettingMoney(0) }
    }
}
