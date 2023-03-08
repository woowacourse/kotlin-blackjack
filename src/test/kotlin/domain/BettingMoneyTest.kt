package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BettingMoneyTest {

    @Test
    fun `베팅 금액은 음수가 될 수 없다`() {
        // given
        val money = -1000
        // when
        // then
        assertThrows<IllegalArgumentException> { BettingMoney(money) }
    }
}
