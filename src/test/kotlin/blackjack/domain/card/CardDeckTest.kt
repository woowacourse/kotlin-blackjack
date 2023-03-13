package blackjack.domain.card

import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드를 하나 뽑는다`() {
        val cardDeck = CardDeck(SPADE_KING, SPADE_JACK)

        assertThat(cardDeck.draw()).isEqualTo(SPADE_KING)
    }
}
