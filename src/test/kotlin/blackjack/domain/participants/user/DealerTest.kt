package blackjack.domain.participants.user

import blackjack.domain.blackjack.blackJackSetting
import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.state.endTurn.Stay
import blackjack.domain.state.inTurn.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `점수가 17이상이면 뽑을 수 없다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
        dealer.draw(Card(CardMark.CLOVER, CardValue.SEVEN))
        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `점수가 17미만이면 뽑을 수 있다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
        dealer.draw(Card(CardMark.CLOVER, CardValue.SIX))
        assertThat(dealer.state).isInstanceOf(Hit::class.java)
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

        blackJack.dealer.draw(Card(CardMark.SPADE, CardValue.NINE))
        blackJack.dealer.draw(Card(CardMark.HEART, CardValue.TEN))

        blackJack.guests[0].draw(Card(CardMark.SPADE, CardValue.TEN))
        blackJack.guests[0].draw(Card(CardMark.HEART, CardValue.TEN))

        blackJack.guests[1].draw(Card(CardMark.SPADE, CardValue.NINE))
        blackJack.guests[1].draw(Card(CardMark.HEART, CardValue.NINE))

        val profit = blackJack.participants.dealer.calculateProfit(blackJack.participants.guests)
        assertThat(profit).isEqualTo(1000)
    }
}
