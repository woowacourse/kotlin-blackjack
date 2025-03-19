package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 한 장 뽑는다`() {
        // Given
        val deck = Deck.createDefaultDeck()

        // When
        deck.draw()

        // Then
        deck.cards.size shouldBe 51
    }

    @Test
    fun `카드가 존재하지 않을 경우 카드를 뽑을 수 없다`() {
        // Given
        val deck = Deck.createCustomDeck(emptyList())

        // Then
        shouldThrowExactly<IllegalArgumentException> { deck.draw() }
    }
}
