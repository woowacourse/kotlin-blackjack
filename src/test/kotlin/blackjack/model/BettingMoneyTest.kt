package blackjack.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class BettingMoneyTest {
    @Test
    fun `최소 베팅 금액에서 최대 베팅 금액 사이에 값이 들어오면 베팅 금액이 생성되어야 한다`() {
        assertDoesNotThrow { BettingMoney(BettingMoney.MIN_MONEY) }
    }

    @Test
    fun `최소 베팅 금액보다 작거나 최대 베팅 금액을 넘으면 에러를 던져야 한다`() {
        val outOfRangeMoney = BettingMoney.MIN_MONEY - BettingMoney.MAX_MONEY
        assertThrows<IllegalArgumentException> { BettingMoney(outOfRangeMoney) }
    }
}
