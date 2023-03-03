package blackjack.domain

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

        assertThat(Outcome.of(dealer, guest)).isEqualTo(Outcome.DRAW)
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

        assertThat(Outcome.of(dealer, guest)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러 점수가 유저 점수보다 크면 진다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.THREE))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.TWO))

        assertThat(Outcome.of(dealer, guest)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `유저 점수가 21을 넘으면 진다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.THREE))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.KING))

        assertThat(Outcome.of(dealer, guest)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `유저 점수가 딜러 점수보다 크면 이긴다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.TWO))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.THREE))

        assertThat(Outcome.of(dealer, guest)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `딜러 점수가 21을 넘으면 이긴다`() {
        val dealer = Dealer()
        val guest = Guest("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.KING))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.HEART, CardValue.SEVEN))
        guest.draw(Card(CardMark.CLOVER, CardValue.THREE))

        assertThat(Outcome.of(dealer, guest)).isEqualTo(Outcome.WIN)
    }
}
