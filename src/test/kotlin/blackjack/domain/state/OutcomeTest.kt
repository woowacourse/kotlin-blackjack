package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.participants.user.Dealer
import blackjack.domain.participants.user.Guest
import blackjack.domain.participants.user.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    @Test
    fun `유저가 두장으로 블랙잭이 되면 딜러가 블랙잭이여도 이긴다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.CLOVER, CardValue.SEVEN))
        dealer.draw(Card(CardMark.CLOVER, CardValue.NINE))

        guest.draw(Card(CardMark.SPADE, CardValue.ACE))
        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.WIN_WITH_BLACKJACK)
    }

    @Test
    fun `유저와 딜러 모두 블랙잭이면 비긴다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.ACE))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))

        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))
        guest.draw(Card(CardMark.SPADE, CardValue.KING))
        guest.draw(Card(CardMark.SPADE, CardValue.ACE))
        print(guest.state)
        print(dealer.state)
        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `유저 혼자 블랙잭이면 이긴다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))

        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))
        guest.draw(Card(CardMark.SPADE, CardValue.KING))
        guest.draw(Card(CardMark.SPADE, CardValue.ACE))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `딜러 혼자 블랙잭이면 진다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.ACE))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))

        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))
        guest.draw(Card(CardMark.SPADE, CardValue.KING))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `딜러와 유저의 점수가 둘 다 21점 초과면 비긴다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
        dealer.draw(Card(CardMark.CLOVER, CardValue.TWO))

        guest.draw(Card(CardMark.SPADE, CardValue.KING))
        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))
        guest.draw(Card(CardMark.SPADE, CardValue.THREE))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러 혼자 점수가 21을 넘으면 유저가 이긴다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))
        dealer.draw(Card(CardMark.CLOVER, CardValue.TWO))

        guest.draw(Card(CardMark.SPADE, CardValue.KING))
        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `유저 혼자 점수가 21점을 넘으면 유저가 진다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))

        guest.draw(Card(CardMark.SPADE, CardValue.KING))
        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))
        guest.draw(Card(CardMark.SPADE, CardValue.TWO))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `딜러와 유저의 점수가 같으면 비긴다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        dealer.draw(Card(CardMark.CLOVER, CardValue.SEVEN))

        guest.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        guest.draw(Card(CardMark.SPADE, CardValue.SEVEN))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `유저 점수가 딜러 점수보다 크면 유저가 이긴다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.NINE))

        guest.draw(Card(CardMark.SPADE, CardValue.KING))
        guest.draw(Card(CardMark.SPADE, CardValue.QUEEN))
        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `딜러 점수가 유저 점수보다 크면 유저가 진다`() {
        val dealer = Dealer()
        val guest = Guest(Name("아크"))
        dealer.draw(Card(CardMark.CLOVER, CardValue.KING))
        dealer.draw(Card(CardMark.CLOVER, CardValue.QUEEN))

        guest.draw(Card(CardMark.SPADE, CardValue.KING))
        guest.draw(Card(CardMark.SPADE, CardValue.NINE))

        assertThat(guest.state.matchWith(dealer.state)).isEqualTo(Outcome.LOSE)
    }
}
