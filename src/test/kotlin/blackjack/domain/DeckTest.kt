package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 한 장 뽑으면 덱에 있는 카드는 51장이다`() {
        val deck = Deck(ShuffledCardGenerator().generate())
        deck.pick()
        assertThat(deck.cards.size).isEqualTo(51)
    }
}
