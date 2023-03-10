package blackjack.domain.participants

import blackjack.domain.blackjack.blackJack
import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.state.FirstTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GuestTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 1_000_000])
    fun `배팅 금액을 반환한다`(money: Int) {
        val user = Guest(Name("아크"), FirstTurn(), Money(money))
        assertThat(user.bettingMoney.value).isEqualTo(money)
    }

    @Test
    fun `버스트가 나지 않고 블랙잭이 아니면 카드를 뽑을 수 있다`() {
        val user = Guest(Name("아크"))
        assertThat(user.isContinuable).isTrue
    }

    @Test
    fun `수익금액을 가져올 수 있다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guest(Name("아크"), Money(1000))
                guest(Name("로피"), Money(2000))
            }
        }

        blackJack.dealer.draw(Card(CardMark.SPADE, CardValue.NINE))
        blackJack.dealer.draw(Card(CardMark.HEART, CardValue.TEN))

        blackJack.guests[0].draw(Card(CardMark.SPADE, CardValue.TEN))
        blackJack.guests[0].draw(Card(CardMark.HEART, CardValue.TEN))

        blackJack.guests[1].draw(Card(CardMark.SPADE, CardValue.NINE))
        blackJack.guests[1].draw(Card(CardMark.HEART, CardValue.NINE))

        assertAll(
            { assertThat(blackJack.guests[0].calculateProfit(blackJack.dealer)).isEqualTo(1000) },
            { assertThat(blackJack.guests[1].calculateProfit(blackJack.dealer)).isEqualTo(-2000) },
        )
    }
}
