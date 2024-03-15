package blackjack.model.card

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardDeckTest {
    @Test
    fun `카드를 100,000 장을 뽑아도 때 예외를 던지지 않는다`() {
        repeat(100_000) {
            assertDoesNotThrow { println(CardDeck.getRandomCard()) }
        }
    }
}
