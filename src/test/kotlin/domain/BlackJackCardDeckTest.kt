package domain

import domain.card.BlackJackCardDeck
import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackJackCardDeckTest {
    @Test
    fun `덱에는 최소한 카드가 한장 이상 있어야 한다`() {
        assertThrows<IllegalStateException> { BlackJackCardDeck(emptyList()) }
    }

    @Test
    fun `카드뭉치에서 위에서 카드를 한 장 얻을 수 있다`() {
        val deckCards = listOf(Card.of(CardCategory.HEART, CardNumber.JACK))
        val deck = BlackJackCardDeck(deckCards)
        val actual = deck.draw()
        val expected = Card.of(CardCategory.HEART, CardNumber.JACK)
        assertThat(actual).isEqualTo(expected)
    }
}
