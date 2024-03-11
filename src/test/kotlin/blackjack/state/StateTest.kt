package blackjack.state

import blackjack.fixture.createCard
import blackjack.model.card.Hand
import blackjack.model.card.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StateTest {
    @Test
    fun `Running 상태에서 hit 하면, hand 가 bust 면 Bust 상태 반환`() {
        // given
        val hand = Hand(createCard(rank = Rank.TEN), createCard(rank = Rank.TEN))
        val card = createCard(rank = Rank.TWO)
        val runningState = State.Running(hand)
        // when
        val actual = runningState.hit(card)
        // then
        assertThat(actual).isInstanceOf(State.Finish.Bust::class.java)
    }

    @Test
    fun `Running 상태에서 hit 하면, hand 가 bust 가 아니면 Running 상태가 된다`() {
        // given
        val hand = Hand(createCard(rank = Rank.TEN), createCard(rank = Rank.TEN))
        val card = createCard(rank = Rank.ACE)
        val runningState = State.Running(hand)
        // when
        val actual = runningState.hit(card)
        // then
        assertThat(actual).isInstanceOf(State.Running::class.java)
    }

    @Test
    fun `Running 상태이고 stay 했을 때, hand 가 blackJack 이면 BlackJack 상태가 된다`() {
        // given
        val hand = Hand(createCard(rank = Rank.TEN), createCard(rank = Rank.ACE))
        val runningState = State.Running(hand)
        // when
        val actual = runningState.stay()
        // then
        assertThat(hand.isBlackjack()).isTrue()
        assertThat(actual).isInstanceOf(State.Finish.BlackJack::class.java)
    }

    @Test
    fun `Running 상태이고 stay 했을 때, hand 가 Bust 면 Bust 상태가 된다`() {
        // given
        val hand = Hand(createCard(rank = Rank.TEN), createCard(rank = Rank.TEN), createCard(rank = Rank.TWO))
        val runningState = State.Running(hand)
        // when
        val actual = runningState.stay()
        // then
        assertThat(actual).isInstanceOf(State.Finish.Bust::class.java)
    }

    @Test
    fun `Running 상태이고 stay 했을 때, hand 가 Bust 도 BlackJack 도 아니면 Stop 상태가 된다`() {
        // given
        val hand = Hand(createCard(rank = Rank.TEN), createCard(rank = Rank.TEN))
        val runningState = State.Running(hand)
        // when
        val actual = runningState.stay()
        // then
        assertThat(actual).isInstanceOf(State.Finish.Stop::class.java)
    }
}
