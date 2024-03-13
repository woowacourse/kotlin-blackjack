package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    private lateinit var cardDeck: CardDeck
    private lateinit var expectedCards: MutableList<Card>

    @BeforeEach
    fun setUp() {
        cardDeck = CardDeck()
        expectedCards = mutableListOf()
        Denomination.entries.forEach { denomination ->
            Suit.entries.forEach { suit ->
                expectedCards.add(Card(denomination, suit))
            }
        }
    }

    @Test
    fun `n장의 덱을 생성하여 뽑았을 때 예상하는 순서대로 카드가 나온다`() {
        expectedCards.forEach { expectedCard ->
            assertThat(cardDeck.draw()).isEqualTo(expectedCard)
        }
    }

    @Test
    fun `생성된 카드를 셔플했을 때 예상하지 못하게 카드가 나온다`() {
        cardDeck.cardShuffle()
        val isAnyCardNotEqual =
            expectedCards.any { expectedCard ->
                cardDeck.draw() != expectedCard
            }
        assertThat(isAnyCardNotEqual).isTrue()
    }

    @Test
    fun `카드 덱에서 뽑을 수 있는 카드가 없을 때, 예외를 던져야한다`() {
        val cardDeck = CardDeck()
        repeat(CardDeck.MAX_DRAW_COUNT) {
            cardDeck.draw()
        }
        assertThrows<IllegalArgumentException> { cardDeck.draw() }
    }
}
