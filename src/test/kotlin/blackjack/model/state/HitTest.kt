package blackjack.model.state

import blackjack.model.TestFixtures.HEART_JACK
import blackjack.model.TestFixtures.HEART_ONE
import blackjack.model.TestFixtures.HEART_TEN
import blackjack.model.TestFixtures.HEART_TWO
import blackjack.model.domain.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {

    @Test
    fun `Hit 상태에서 카드를 받았는데 카드의 합이 21보다 크면 Bust`() {
        val hit = Hit(Hand(setOf(HEART_JACK, HEART_TEN), 0))
        val actual = hit.draw(HEART_TWO)

        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit 상태에서 카드를 받았는데 카드의 합이 21보다 작으면 Hit`() {
        val hit = Hit(Hand(setOf(HEART_ONE, HEART_TEN), 0))
        val actual = hit.draw(HEART_TWO)

        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit 상태에서 카드를 받았는데 블랙잭이면 BlackJack`() {
        val hit = Hit(Hand(setOf(HEART_ONE), 0))
        val actual = hit.draw(HEART_TEN)

        assertThat(actual).isInstanceOf(BlackJack::class.java)
    }
}
