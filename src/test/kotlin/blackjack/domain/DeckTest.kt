package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에서 카드 넘버와 카드 모양을 조합한 모든 경우의 카드를 뽑을 수 있다`() {
        val cards = mutableSetOf<Card>()
        repeat(52) {
            val card = Deck.draw()
            cards.add(card)
        }
        assertThat(cards.size).isEqualTo(52)
    }
}
