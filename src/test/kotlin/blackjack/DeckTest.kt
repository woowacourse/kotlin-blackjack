package blackjack

import blackjack.domain.card.CardTier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import blackjack.domain.deck.ShuffledDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `순서대로 정렬된 카드의 덱에서 첫 번째 카드를 뽑으면 순서에 따라 Ace하트 카드가 나와야 한다`() {
        val deck = OrderedDeck()
        assertThat(deck.pop()).isEqualTo(TrumpCard(CardTier.ACE, Shape.HEART))
    }

    @Test
    fun `8개의 덱에 있는 카드 이상의 카드를 뽑을 수 없다`() {
        val deck = ShuffledDeck()

        repeat(416) { deck.pop() }

        assertThrows<IllegalArgumentException> { deck.pop() }
    }
}
