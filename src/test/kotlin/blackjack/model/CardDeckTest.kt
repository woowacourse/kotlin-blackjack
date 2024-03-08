package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
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

        val cardDeck = CardDeck()
        repeat(CardDeck.MAX_DRAW_COUNT) {
            val card =
                cardDeck.draw().also { card ->
                    assertThat(
                        actualCards.find { actualCard ->
                            val compareDenomination = actualCard.getCardDenomination() == card.getCardDenomination()
                            val compareSuit = actualCard.getCardSuit() == card.getCardSuit()
                            compareDenomination && compareSuit
                        },
                    ).isNotEqualTo(null)
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
