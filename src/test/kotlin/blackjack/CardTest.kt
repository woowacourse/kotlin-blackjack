package blackjack

import blackjack.domain.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `모든 카드는 중복없이 52장이 존재한다`() {
        val allCards = Card.getAllCard()

        assertThat(allCards).hasSize(52)
        assertThat(allCards.distinct()).hasSize(52)
    }
}
