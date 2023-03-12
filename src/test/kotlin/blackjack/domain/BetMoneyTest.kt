package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetMoneyTest {
    @Test
    fun `베팅하는 금액은 1000원 단위여야한다`() {
        val betMoney = BetMoney(1000)
        assertThat(betMoney.money).isEqualTo(1000)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -1000, -500])
    fun `베팅하는 금액이 0또는 음수면은 에러를 발생시킨다`(money: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { BetMoney(money) }
            .withMessageContaining("베팅하는 금액은 0이거나 음수일 수 없습니다. (잘못된 값: $money)")
    }

    @ParameterizedTest
    @ValueSource(ints = [999, 100, 500, 1001])
    fun `베팅하는 금액이 1000원 단위가 아니라면 에러를 발생시킨다`(money: Int) {
        assertThatIllegalArgumentException()
            .isThrownBy { BetMoney(money) }
            .withMessageContaining("베팅하는 금액은 1000원 단위여야 합니다. (잘못된 값: $money)")
    }

    @Test
    fun `블랙잭이라면 보너스로 상금을 더 받는다`() {
        val betMoney = BetMoney(1000)
        assertThat(betMoney.addBlackjackPrizeMoney()).isEqualTo(1500)
    }
}
