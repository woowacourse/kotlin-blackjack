package blackjack.domain.participants.user

import blackjack.domain.blackjack.blackJackData
import blackjack.domain.state.Fixtures.CLOVER_EIGHT
import blackjack.domain.state.Fixtures.CLOVER_NINE
import blackjack.domain.state.Fixtures.CLOVER_SEVEN
import blackjack.domain.state.Fixtures.CLOVER_TEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GuestTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 1_000_000])
    fun `배팅 금액을 반환한다`(money: Int) {
        val user = Guest(Money(money))
        assertThat(user.bettingMoney.value).isEqualTo(money)
    }

    @Test
    fun `수익금액을 가져올 수 있다`() {
        val data = blackJackData {
            participants {
                dealer()
                guest(Name("아크"), Money(1000))
                guest(Name("로피"), Money(2000))
            }
        }

        data.dealer.draw(CLOVER_NINE)
        data.dealer.draw(CLOVER_EIGHT)

        data.guests[0].draw(CLOVER_TEN)
        data.guests[0].draw(CLOVER_NINE)

        data.guests[1].draw(CLOVER_NINE)
        data.guests[1].draw(CLOVER_SEVEN)

        assertAll(
            { assertThat(data.guests[0].calculateProfit(data.dealer)).isEqualTo(1000) },
            { assertThat(data.guests[1].calculateProfit(data.dealer)).isEqualTo(-2000) },
        )
    }
}
