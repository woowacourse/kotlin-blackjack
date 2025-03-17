package blackjack

import blackjack.card.Card
import blackjack.card.Deck
import blackjack.card.Rank
import blackjack.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException

class DeckTest {
    private val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
    private val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
    private val card3 = Card.of(rank = Rank.NINE, suit = Suit.HEART)

    @Test
    fun `덱에 카드가 한 장 있을 때, 카드를 한 장 뽑으면, 덱의 크기가 0이된다`() {
        val cards = listOf(card1)
        val deck = Deck(cards)

        deck.draw()

        assertThatThrownBy { deck.draw() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("덱이 비어 있습니다")
    }

    @Test
    fun `뽑은 카드는 덱에 존재하지 않는다`() {
        val cards = listOf(card1, card2, card3)
        val deck = Deck(cards)

        val drawnCard = deck.draw()

        assertThat(drawnCard).isEqualTo(cards.first())
    }
}
