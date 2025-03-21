package blackjack.domain.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `덱에서 카드를 한장 줄 수 있다`() {
        val deck = Deck(ArrayDeque(listOf(Card(CardNumber.ACE, Suit.SPADE))))

        val card = deck.popCard()

        assertThat(card).isEqualTo(Card(CardNumber.ACE, Suit.SPADE))
    }

    @Test
    fun `덱이 비어있으면 카드를 줄 수 없다`() {
        val deck = Deck(ArrayDeque(listOf()))

        assertThrows<IllegalArgumentException> { deck.popCard() }
    }
}
