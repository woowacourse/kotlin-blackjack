package blackjack.domain.model.card

import blackjack.domain.generator.ShuffledCardsGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 원하는 수만큼 뽑을 수 있다`() {
        val actual: List<Card> = Deck(ShuffledCardsGenerator()).pop(5) ?: listOf()

        val expectedSize = 5

        assertThat(actual.size).isEqualTo(expectedSize)
    }
}
