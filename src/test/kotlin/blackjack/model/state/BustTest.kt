package blackjack.model.state

import blackjack.model.TestFixtures.HEART_JACK
import blackjack.model.TestFixtures.HEART_KING
import blackjack.model.TestFixtures.HEART_ONE
import blackjack.model.TestFixtures.HEART_QUEEN
import blackjack.model.domain.Hand
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BustTest {

    @Test
    fun `Bust 상태에서 카드를 받으면 예외를 던지는 지`() {
        val bust = Bust(Hand(setOf(HEART_JACK, HEART_QUEEN, HEART_KING), 0))

        assertThrows<IllegalArgumentException> { bust.draw(HEART_ONE) }
    }
}
