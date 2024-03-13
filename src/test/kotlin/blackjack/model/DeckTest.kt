package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `히트를 하면 덱에서 한장 가져온다`() {
        // given
        val cards = mutableListOf(createCard(), createCard(), createCard(), createCard())
        val deck = Deck(cards)
        // when
        val actual = deck.pull()
        // then
        assertThat(actual).isEqualTo(createCard())
    }
}
