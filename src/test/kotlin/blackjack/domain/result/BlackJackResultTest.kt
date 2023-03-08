package blackjack.domain.result

import blackjack.domain.blackjack.blackJack
import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackResultTest {
    @Test
    fun `수익금액을 가져올 수 있다`() {
        val blackJack = blackJack {
            participants {
                dealer()
                guest("아크", 1000)
                guest("로피", 2000)
            }
        }

        blackJack.dealer.draw(Card(CardMark.SPADE, CardValue.NINE))
        blackJack.dealer.draw(Card(CardMark.HEART, CardValue.TEN))

        blackJack.guests[0].draw(Card(CardMark.SPADE, CardValue.TEN))
        blackJack.guests[0].draw(Card(CardMark.HEART, CardValue.TEN))

        blackJack.guests[1].draw(Card(CardMark.SPADE, CardValue.NINE))
        blackJack.guests[1].draw(Card(CardMark.HEART, CardValue.NINE))

        val blackJackResult = BlackJackResult(blackJack.participants)

        assertAll(
            { assertThat(blackJackResult.dealer.rateOfReturn).isEqualTo(1000) },
            { assertThat(blackJackResult.guests[0].rateOfReturn).isEqualTo(1000) },
            { assertThat(blackJackResult.guests[1].rateOfReturn).isEqualTo(-2000) },
        )
    }
}
