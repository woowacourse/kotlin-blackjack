package blackjack.domain.participants.user

import blackjack.domain.blackjack.blackJackData
import blackjack.domain.state.Fixtures.CLOVER_EIGHT
import blackjack.domain.state.Fixtures.CLOVER_NINE
import blackjack.domain.state.Fixtures.CLOVER_QUEEN
import blackjack.domain.state.Fixtures.CLOVER_SEVEN
import blackjack.domain.state.Fixtures.CLOVER_SIX
import blackjack.domain.state.Fixtures.CLOVER_TEN
import blackjack.domain.state.Hit
import blackjack.domain.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `점수가 17이상이면 카드를 뽑을 수 없다`() {
        val dealer = Dealer()
        dealer.draw(CLOVER_QUEEN)
        dealer.draw(CLOVER_SEVEN)
        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `점수가 17미만이면 카드를 뽑을 수 있다`() {
        val dealer = Dealer()
        dealer.draw(CLOVER_QUEEN)
        dealer.draw(CLOVER_SIX)
        assertThat(dealer.state).isInstanceOf(Hit::class.java)
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

        val profit = data.participants.dealer.calculateProfit(data.participants.guests)
        assertThat(profit).isEqualTo(1000)
    }
}
