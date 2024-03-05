package blackjack

import blackjack.model.Deck
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드 덱의 크기는 52개이다`() {
        val deck = Deck()
        println(deck.cards)
        Assertions.assertThat(deck.cards.size).isEqualTo(52)
    }
}
