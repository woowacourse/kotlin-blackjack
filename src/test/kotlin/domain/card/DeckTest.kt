package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DeckTest {

    @Test
    fun `덱에서 한장 뽑으면 트럼프 카드가 나온다`() {
        val card = Deck.create().getCard()
        assertAll(
            { assertThat(card.shape).isIn(CardShape.values().toList()) },
            { assertThat(card.number).isIn(CardNumber.values().toList()) },
        )
    }
}
