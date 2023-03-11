package blackjack.domain.participants

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UsersBettingMoneyTest {

    @Test
    fun `참가자의 베팅금액을 확인할 수 있다`() {
        val guest = Guest(Name("로피"))
        val bettingMoney = BettingMoney(20000)
        val usersBettting = UsersBettingMoney(
            mapOf(guest to bettingMoney),
        )
        assertThat(usersBettting.getUserBettingMoney(guest)).isEqualTo(bettingMoney)
    }
}
