package blackjack.domain.card

import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.card.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `카드는 417장 이상 반환될 수 없다`() {
        // given
        val cardDeck = CardDeck()
        val exceedCount = CardCount(417)

        // when & then
        assertThrows<IllegalArgumentException> {
            cardDeck.draw(exceedCount)
        }
    }

    @Test
    fun `카드는 총 416장까지 뽑을 수 있다`() {
        // given
        val cardDeck = CardDeck()
        val cardCount = CardCount(416)

        // when
        val cards: List<Card> = cardDeck.draw(cardCount)

        // then
        assertThat(CardCount(cards.size)).isEqualTo(cardCount)
    }
}
