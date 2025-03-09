package blackjack

import blackjack.model.card.Deck
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DeckTest {
    @Test
    fun `덱에 존재하는 52장 카드를 모두 사용하면 새로운 카드 뭉치의 카드를 사용한다`() {
        val deck = Deck.create()

        assertDoesNotThrow {
            repeat(53) {
                deck.draw()
            }
        }
    }
}
