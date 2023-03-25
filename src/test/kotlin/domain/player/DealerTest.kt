package domain.player

import model.domain.player.Dealer
import model.tools.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `유저의 배팅금액 500이 딜러의 배팅금액이다`() {
        // given
        val dealer = Dealer.from()
        val userTotalMoney = Money(500)

        // when
        dealer.betMoney(userTotalMoney)
        val actual = dealer.money.amount

        // then
        assertThat(actual).isEqualTo(userTotalMoney.amount)
    }

    @Test
    fun `유저의 최종 합산금액이 500이 딜러의 수익이다`() {
        // given
        val dealer = Dealer.from()
        val userTotalMoney = Money(-500)

        // when
        dealer.calculateTotalMoney(userTotalMoney.amount)
        val actual = dealer.money.amount

        // then
        assertThat(actual).isEqualTo(500)
    }
}
