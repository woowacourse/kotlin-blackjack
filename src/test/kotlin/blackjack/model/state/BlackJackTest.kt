package blackjack.model.state

import blackjack.model.TestFixtures.HEART_JACK
import blackjack.model.TestFixtures.HEART_ONE
import blackjack.model.TestFixtures.HEART_TWO
import blackjack.model.domain.Hand
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackJackTest {

    @Test
    fun `BlackJack 상태에서 카드를 받으면 예외를 던지는 지`() {
        val blackjack = BlackJack(Hand(setOf(HEART_JACK, HEART_ONE), 0))

        assertThrows<IllegalArgumentException> { blackjack.draw(HEART_TWO) }
    }
}
