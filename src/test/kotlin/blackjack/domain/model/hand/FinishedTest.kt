package blackjack.domain.model.hand

import blackjack.domain.HEART_ACE
import blackjack.domain.model.Card
import blackjack.domain.model.Rank
import blackjack.domain.model.Suit
import blackjack.domain.model.hand.state.Finished
import blackjack.domain.model.hand.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FinishedTest {
    private lateinit var finished: Finished

    @BeforeEach
    fun setUp() {
        finished = Stay(Hands(HEART_ACE))
    }

    @Test
    fun `Finished에서는 카드를 더 받을을 수 없다 `() {
        assertThatThrownBy {
            finished.nextState(
                Card(
                    Suit.HEART,
                    Rank.KING,
                ),
            )
        }.isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `Finished는 끝난 상태다`() {
        assertThat(finished.isFinished()).isTrue()
    }

    @Test
    fun `Finished는 시작 상태가 아니다`() {
        assertThat(finished.isStarted()).isFalse()
    }
}
