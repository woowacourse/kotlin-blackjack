package domain

import domain.card.BlackJackCardDeck
import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class BlackJackCardDeckTest {
    @Test
    fun `덱에는 최소한 카드가 한장 이상 있어야 한다`() {
        assertThrows<IllegalStateException> { BlackJackCardDeck(emptyList()) }
    }

    @Test
    fun `카드뭉치에서 위에서부터 두 장의 카드를 얻을 수 있다`() {
        val deckCards = listOf(Card.of(CardCategory.HEART, CardNumber.JACK)) +
            List(51) { Card.of(CardCategory.SPADE, CardNumber.THREE) }
        val deck = BlackJackCardDeck(deckCards)
        val cards = deck.drawInitCards()

        assertAll(
            "카드뭉치에서 위에서부터 두 장을 얻는다",
            { assertThat(cards.list[0]).isEqualTo(Card.of(CardCategory.HEART, CardNumber.JACK)) },
            { assertThat(cards.list[1]).isEqualTo(Card.of(CardCategory.SPADE, CardNumber.THREE)) },
            { assertThat(cards.size).isEqualTo(2) }
        )
    }
}
