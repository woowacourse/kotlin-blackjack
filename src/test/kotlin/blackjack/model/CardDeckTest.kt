package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.LinkedList

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
    fun `생성된 카드 덱을 셔플하면 카드가 섞여야 한다`() {
        val shuffledCardDeck = cardDeck.cardShuffle()
        Assertions.assertNotEquals(cardDeck, shuffledCardDeck)
    }

    @Test
    fun `2스페이드, 3다이아, 4하트, 5클로버 구성의 카드덱을 설정했을 때 카드를 뽑으면 같은 순서로 나와야 한다`() {
        val cards =
            LinkedList<Card>().apply {
                add(Card(Denomination.TWO, Suit.SPADE))
                add(Card(Denomination.THREE, Suit.DIAMOND))
                add(Card(Denomination.FOUR, Suit.HEART))
                add(Card(Denomination.FIVE, Suit.CLOVER))
            }
        val cardDeck = CardDeck(cards = LinkedList(cards))

        for (i in 0 until 4) {
            val drawnCard = cardDeck.draw()
            assertThat(cards[i]).isEqualTo(drawnCard)
        }
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
