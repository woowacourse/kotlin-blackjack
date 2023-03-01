package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    @Test
    fun `딜러와 유저의 점수가 같으면 비긴다`() {
        val dealer = User("딜러")
        val user = User("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))

        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        user.draw(Card(CardMark.HEART, CardValue.SEVEN))

        assertThat(Outcome.of(dealer, user)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러와 유저의 점수가 둘 다 21점 이상이면 비긴다`() {
        val dealer = User("딜러")
        val user = User("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.NINE))

        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        user.draw(Card(CardMark.HEART, CardValue.SEVEN))
        user.draw(Card(CardMark.CLOVER, CardValue.QUEEN))

        assertThat(Outcome.of(dealer, user)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러 점수가 유저 점수보다 크면 진다`() {
        val dealer = User("딜러")
        val user = User("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.THREE))

        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        user.draw(Card(CardMark.HEART, CardValue.SEVEN))
        user.draw(Card(CardMark.CLOVER, CardValue.TWO))

        assertThat(Outcome.of(dealer, user)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `유저 점수가 21을 넘으면 진다`() {
        val dealer = User("딜러")
        val user = User("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.THREE))

        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        user.draw(Card(CardMark.HEART, CardValue.SEVEN))
        user.draw(Card(CardMark.CLOVER, CardValue.KING))

        assertThat(Outcome.of(dealer, user)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `유저 점수가 딜러 점수보다 크면 이긴다`() {
        val dealer = User("딜러")
        val user = User("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.TWO))

        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        user.draw(Card(CardMark.HEART, CardValue.SEVEN))
        user.draw(Card(CardMark.CLOVER, CardValue.THREE))

        assertThat(Outcome.of(dealer, user)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `딜러 점수가 21을 넘으면 이긴다`() {
        val dealer = User("딜러")
        val user = User("아크")
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.DIA, CardValue.SEVEN))
        dealer.draw(Card(CardMark.HEART, CardValue.KING))

        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        user.draw(Card(CardMark.HEART, CardValue.SEVEN))
        user.draw(Card(CardMark.CLOVER, CardValue.THREE))

        assertThat(Outcome.of(dealer, user)).isEqualTo(Outcome.WIN)
    }
}
