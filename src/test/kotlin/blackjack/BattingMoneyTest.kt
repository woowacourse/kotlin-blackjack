package blackjack

import blackjack.model.BattingMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BattingMoneyTest {
    @Test
    fun `초기 배팅 금액이 음수일 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BattingMoney.ofAmount(-100)
        }
    }

    @Test
    fun `배팅 금액끼리 서로 더할 수 있다`() {
        val money = BattingMoney.ofAmount(100) + BattingMoney.ofAmount(100)
        assertThat(money.amount).isEqualTo(200)
    }

    @Test
    fun `배팅 금액끼리 서로 뺄 수 있다`() {
        val money = BattingMoney.ofAmount(200) - BattingMoney.ofAmount(100)
        assertThat(money.amount).isEqualTo(100)
    }

    @Test
    fun `초기 배팅 이후의 금액은 음수가 될 수 있다`() {
        val money = BattingMoney.ofAmount(100) - BattingMoney.ofAmount(200)
        assertThat(money.amount).isEqualTo(-100)
    }

    @Test
    fun `배팅 금액을 곱할 수 있다`() {
        val money = BattingMoney.ofAmount(100).times(2.0)
    }
}
