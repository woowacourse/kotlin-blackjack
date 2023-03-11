package blackjack.domain.participants.user

import blackjack.domain.blackjack.blackJackSetting
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
        val blackJack = blackJackSetting {
            participants {
                dealer()
                guest(Name("아크"), Money(1000))
                guest(Name("로피"), Money(2000))
            }
        }

        blackJack.dealer.draw(CLOVER_NINE)
        blackJack.dealer.draw(CLOVER_EIGHT)

        blackJack.guests[0].draw(CLOVER_TEN)
        blackJack.guests[0].draw(CLOVER_NINE)

        blackJack.guests[1].draw(CLOVER_NINE)
        blackJack.guests[1].draw(CLOVER_SEVEN)

        assertAll(
            { assertThat(blackJack.guests[0].calculateProfit(blackJack.dealer)).isEqualTo(1000) },
            { assertThat(blackJack.guests[1].calculateProfit(blackJack.dealer)).isEqualTo(-2000) },
        )
    }
}
