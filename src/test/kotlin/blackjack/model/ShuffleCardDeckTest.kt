package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ShuffleCardDeckTest {
    @Test
    fun `올바른 카드 덱 생성 테스트`() {
        var actualCards: Set<Card> =
            Denomination.entries.flatMap { denomination ->
                Suit.entries.map { suit ->
                    Card(
                        denomination = denomination,
                        suit = suit,
                    )
                }
            }.toSet()

        val shuffleCardDeck = ShuffleCardDeck()
        repeat(ShuffleCardDeck.MAX_DRAW_COUNT) {
            val card =
                shuffleCardDeck.draw().also { card ->
                    assertThat(
                        actualCards.find { actualCard ->
                            val compareDenomination = actualCard.denomination == card.denomination
                            val compareSuit = actualCard.suit == card.suit
                            compareDenomination && compareSuit
                        },
                    ).isNotEqualTo(null)
                }
            actualCards = actualCards - card
        }
    }

    @Test
    fun `카드 덱에서 뽑을 수 있는 카드가 없을 때, 예외를 던져야한다`() {
        val shuffleCardDeck = ShuffleCardDeck()
        repeat(ShuffleCardDeck.MAX_DRAW_COUNT) {
            shuffleCardDeck.draw()
        }
        assertThrows<IllegalArgumentException> { shuffleCardDeck.draw() }
    }
}
