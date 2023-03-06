package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드 1장 뽑기`() {
        // given
        val expectedCard = Card(CardShape.SPADES, CardValue.ACE)
        val deck = Deck.create(1) {
            listOf<Card>(
                expectedCard
            )
        }

        // when
        val actual = deck.getOneCard()

        // then
        assertThat(actual).isEqualTo(expectedCard)
    }

    @Test
    fun `카드 2장 뽑기`() {
        // given
        val expectedCards =
            listOf(
                Card(CardShape.SPADES, CardValue.ACE),
                Card(CardShape.SPADES, CardValue.QUEEN),
            )
        val deck = Deck.create(1) { expectedCards }

        // when
        val actual = deck.getCards(2)

        // then
        assertThat(actual).isEqualTo(expectedCards)
    }
}
