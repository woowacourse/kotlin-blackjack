package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 발행이 가능한지 확인한다`() {
        // given
        val cardDeck = CardDeck()

        // when
        val actual = cardDeck.checkProvidePossible()

        // then
        assertThat(actual).isTrue
    }
}
