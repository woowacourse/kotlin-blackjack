package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱은 중복없이 52장의 카드를 갖고 있고, 52장을 모두 뽑은 후에 다시 한 장을 추가로 뽑을 수 있다`() {
        val cards = mutableSetOf<Card>()
        repeat(53) {
            cards.add(Deck.draw())
        }

        assertThat(cards.size).isEqualTo(52)
    }
}
