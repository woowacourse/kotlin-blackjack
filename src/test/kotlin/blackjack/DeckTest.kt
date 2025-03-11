package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.IllegalArgumentException

class DeckTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setUp() {
        testDeck = Deck(Card.getAllCard())
    }

    @Test
    fun `카드를 한 장 뽑으면, 덱의 크기가 1 줄어든다`() {
        val originalSize = testDeck.getSize()

        testDeck.draw()

        val newSize = testDeck.getSize()
        assertThat(newSize).isEqualTo(originalSize - 1)
    }

    @Test
    fun `덱이 비어있을 때, 카드를 뽑으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            repeat(testDeck.getSize() + 1) { testDeck.draw() }
        }
    }

    @Test
    fun `뽑은 카드는 덱에 존재하지 않는다`() {
        val drawnCard = testDeck.draw()

        assertThat(testDeck.contains(drawnCard)).isFalse()
    }
}
