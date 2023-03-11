package blackjack.domain.participants.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.state.endTurn.BlackJack
import blackjack.domain.state.inTurn.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `카드를 뽑을 수 있다`() {
        val user = Guest()
        user.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        assertThat(user.state.size).isEqualTo(1)
    }

    @Test
    fun `점수의 합을 반환한다`() {
        val user = Guest()
        user.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        assertThat(user.score.value).isEqualTo(16)
    }

    @Test
    fun `ACE를 2장 뽑아도 히트다`() {
        val user = Guest()
        user.draw(Card(CardMark.CLOVER, CardValue.ACE))
        user.draw(Card(CardMark.SPADE, CardValue.ACE))
        println(user.state)
        assertThat(user.state is Hit).isTrue
    }

    @Test
    fun `ACE한장과 10이 나오면 블랙잭이다`() {
        val user = Guest()
        user.draw(Card(CardMark.CLOVER, CardValue.ACE))
        user.draw(Card(CardMark.SPADE, CardValue.KING))
        assertThat(user.state is BlackJack).isTrue
    }
}
