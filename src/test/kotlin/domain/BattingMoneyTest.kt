package domain

import blackjack.domain.player.BattingMoney
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class BattingMoneyTest {

    @Test
    fun `0원 이하의 금액을 배팅한 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BattingMoney(0)
        }
    }

    @Test
    fun `0원보다 큰 금액을 배팅헤야한다`() {
        assertDoesNotThrow {
            BattingMoney(1000)
        }
    }
}
