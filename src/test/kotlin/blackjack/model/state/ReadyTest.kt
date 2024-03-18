package blackjack.model.state

import blackjack.model.TestFixtures.HEART_ONE
import blackjack.model.TestFixtures.HEART_TWO
import blackjack.model.domain.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReadyTest {

    @Test
    fun `Ready 상태에서 카드를 받으면 Hit`() {
        val ready = Ready(Hand(setOf(HEART_ONE), 0))
        val actual = ready.draw(HEART_TWO)

        assertThat(actual).isInstanceOf(Hit::class.java)
    }
}
