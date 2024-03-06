package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱에는 카드 52장이 들어있다`() {
        val deck = Deck()
        val actual = deck.pick()
        assertThat(actual).isEqualTo(52)
    }
}
