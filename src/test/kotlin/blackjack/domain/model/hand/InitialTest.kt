package blackjack.domain.model.hand

import blackjack.domain.HEART_ACE
import blackjack.domain.HEART_SIX
import blackjack.domain.HEART_TEN
import blackjack.domain.model.Card
import blackjack.domain.model.Rank
import blackjack.domain.model.Suit
import blackjack.domain.model.hand.state.BlackJack
import blackjack.domain.model.hand.state.Hit
import blackjack.domain.model.hand.state.Initial
import blackjack.domain.model.hand.strategy.DealerStay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InitialTest {
    private lateinit var initial: Initial

    @BeforeEach
    fun setUp() {
        initial = Initial(DealerStay(), Hands())
    }

    @Test
    fun `카드를 받을때 카드가 2장이고 21일 경우 블랙잭을 반환한다`() {
        initial.nextState(HEART_TEN)
        assertThat(initial.nextState(HEART_ACE)).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `카드를 받을때 카드가 2장일 경우 Hit 상태가 된다`() {
        initial.nextState(HEART_SIX)
        assertThat(initial.nextState(Card(Suit.HEART, Rank.FOUR))).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드를 받을때 카드가 2장 미만일 경우 초기 상태가 된다`() {
        assertThat(initial.nextState(HEART_SIX)).isInstanceOf(Initial::class.java)
    }
}
