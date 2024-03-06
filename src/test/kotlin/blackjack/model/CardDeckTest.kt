package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {

    class CardDeck {
        private var _cards: Set<Card> = Denomination.entries.flatMap { denomination ->
            Suit.entries.map { suit ->
                Card(
                    denomination = denomination,
                    suit = suit
                )
            }
        }.toSet()

        private val cards: Set<Card> get() = _cards.toSet()

        fun draw(): Card {
            val popCard = _cards
                .shuffled()
                .take(DRAW_COUNT)
                .firstOrNull() ?: throw IllegalArgumentException(EMPTY_CARD_DECK)
            _cards -= popCard
            return popCard
        }

        companion object {
            private const val DRAW_COUNT: Int = 1
            private const val EMPTY_CARD_DECK = "카드 덱에서 뽑을 수 있는 카드가 없습니다."
            const val MAX_DRAW_COUNT: Int = 52
        }
    }

    @Test
    fun `올바른 카드 덱 생성 테스트`() {
        var actualCards: Set<Card> = Denomination.entries.flatMap { denomination ->
            Suit.entries.map { suit ->
                Card(
                    denomination = denomination,
                    suit = suit
                )
            }
        }.toSet()

        val cardDeck = CardDeck()
        repeat(CardDeck.MAX_DRAW_COUNT) {
            val card = cardDeck.draw().also { card ->
                assertThat(actualCards.find { actualCard ->
                    "${actualCard.getScore()}${actualCard.getSuit()}" == "${card.getScore()}${card.getSuit()}"
                }).isNotEqualTo(null)
            }
            actualCards = actualCards - card
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
