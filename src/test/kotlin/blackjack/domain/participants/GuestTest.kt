package blackjack.domain.participants

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GuestTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 1_000_000])
    fun `배팅 금액을 반환한다`(money: Int) {
        val user = Guest("아크", money)
        Assertions.assertThat(user.bettingMoney.toInt()).isEqualTo(money)
    }
}
