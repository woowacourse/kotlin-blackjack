package blackjack.domain.participants

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GuestTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 1_000_000])
    fun `배팅 금액을 반환한다`(money: Int) {
        val user = Guest("아크", Money(money))
        assertThat(user.bettingMoney.value).isEqualTo(money)
    }

    @Test
    fun `버스트가 나지 않고 블랙잭이 아니면 카드를 뽑을 수 있다`() {
        val user = Guest("아크")
        assertThat(user.isContinuable).isTrue
    }
}
