package blackjack

import blackjack.model.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    private val deck = Deck()

    @Test
    fun `덱 하나는 52장의 카드를 가진다`() {
        assertThat(deck.cards.size).isEqualTo(CARD_DECK_SIZE)
    }

    @Test
    fun `덱에서 카드를 뽑을 수 없다면 예외를 발생시킨다`() {
        repeat(CARD_DECK_SIZE) {
            deck.draw()
        }
        assertThrows<IllegalStateException> { deck.draw() }
    }

    companion object {
        const val CARD_DECK_SIZE = 52
    }
}
