package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드를 하나 뽑는다`() {
        val cardDeck = CardDeck(TestCardGenerator(mutableListOf(1)))

        assertThat(cardDeck.draw()).isEqualTo(Card.of(1))
    }

    @Test
    fun `아직 뽑히지 않은 카드 중에 하나여야 한다`() {
        val cardDeck = CardDeck(TestCardGenerator(mutableListOf(1, 1, 2, 1, 2, 3)))
        cardDeck.draw() // 1
        cardDeck.draw() // 2
        assertThat(cardDeck.draw()).isEqualTo(Card.of(3)) // 3
    }

    class TestCardGenerator(private val numbers: MutableList<Int>) : CardGenerator {
        override fun generate(): Card = Card.of(numbers.removeAt(0))
    }
}
