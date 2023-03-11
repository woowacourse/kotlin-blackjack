package blackjack.domain.participants.user

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_EIGHT
import blackjack.domain.state.Fixtures.CLOVER_JACK
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_QUEEN
import blackjack.domain.state.Fixtures.CLOVER_SEVEN
import blackjack.domain.state.endTurn.BlackJack
import blackjack.domain.state.endTurn.Bust
import blackjack.domain.state.endTurn.Stay
import blackjack.domain.state.inTurn.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class UserTest {
    @Test
    fun `카드를 뽑을 수 있다`() {
        val user = Guest()
        user.draw(CLOVER_ACE)
        assertThat(user.state.size).isEqualTo(1)
    }

    @Test
    fun `점수의 합을 반환한다`() {
        val user = Guest(state = Hit(Cards(CLOVER_EIGHT, CLOVER_SEVEN)))
        assertThat(user.score).isEqualTo(15)
    }

    @Test
    fun `유저가 히트인지 알 수 있다`() {
        val user = Guest(state = Hit(Cards(CLOVER_ACE, CLOVER_ACE)))
        assertThat(user.isHit).isTrue
    }

    @Test
    fun `유저가 히트가 아닌지 알 수 있다`() {
        val user = Guest()
        assertAll(
            {
                user.state = BlackJack(Cards(CLOVER_ACE, CLOVER_KING))
                assertThat(user.isHit).isFalse
            },
            {
                user.state = Stay(Cards(CLOVER_ACE, CLOVER_ACE))
                assertThat(user.isHit).isFalse
            },
            {
                user.state = Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_JACK))
                assertThat(user.isHit).isFalse
            },
        )
    }
}
