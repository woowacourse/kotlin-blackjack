package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BettingAmountTest {
    @Test
    fun `베팅 금액이 0원 이하면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException>(
            message = "베팅 금액은 0원 이상 입력해주세요.",
        ) {
            BettingAmount(0)
        }
    }
}
