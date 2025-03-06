package blackjack

import blackjack.model.Deck
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `덱에서 카드를 뽑을 수 없다면 예외를 발생시킨다`() {
        val totalCardCount = 52

        assertDoesNotThrow { Deck.drawWithCount(totalCardCount) }
        assertThrows<IllegalStateException> { Deck.draw() }
    }
}