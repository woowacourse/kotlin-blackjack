package blackjack.domain.result

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Guest
import blackjack.domain.result.Outcome.Companion.gameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    @Test
    fun `딜러와 유저의 점수가 같으면 비긴다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))

        assertThat(gameResult(guest, dealer)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러와 유저의 점수가 둘 다 21점 이상이면 비긴다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.NINE))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.QUEEN))

        assertThat(gameResult(guest, dealer)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러 점수가 유저 점수보다 크면 유저가 진다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.THREE))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.TWO))

        assertThat(gameResult(guest, dealer)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `유저 점수가 21을 넘으면 유저가 진다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.THREE))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.KING))

        assertThat(gameResult(guest, dealer)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `유저 점수가 딜러 점수보다 크면 유저가 이긴다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.TWO))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.THREE))

        assertThat(gameResult(guest, dealer)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `딜러 점수가 21을 넘으면 유저가 이긴다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.KING))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.THREE))

        assertThat(gameResult(guest, dealer)).isEqualTo(Outcome.WIN)
    }
}
