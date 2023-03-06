package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `3명일 경우, 카드 세 쌍을 뽑는다`() {
        // given
        val count = 3
        val deck = Deck.create(1)

        // when
        val actual = deck.getCardPairs(count)

        // then
        assertThat(actual).hasSize(3)
    }
}
