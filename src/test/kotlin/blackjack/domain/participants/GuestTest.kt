package blackjack.domain.participants

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GuestTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 1_000_000])
    fun `배팅 금액을 반환한다`(money: Int) {
        val user = Guest("아크", money)
        assertThat(user.bettingMoney.toInt()).isEqualTo(money)
    }

    @Test
    fun `버스트가 아지 않고 블랙잭이아니면 카드를 뽑을 수 있다`() {
        val user = Guest("아크")
        assertThat(user.isContinue).isTrue
    }
}
