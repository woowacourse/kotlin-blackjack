package blackjack.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 300_000_001])
    fun `베팅 금액이 1원 이상 최대 3억원이 아닐 경우 예외를 발생시킨다`(money: Int) {
        assertThrows<IllegalArgumentException> { BettingMoney(money) }
    }
}
