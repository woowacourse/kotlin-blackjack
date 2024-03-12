package blackjack

import blackjack.model.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `카드 뭉치 생성`() {
        val deck = CardDeck()

        val actual = deck.cards

        assertThat(actual.size).isEqualTo(52)
    }

    @Test
    fun `카드 한장 뽑아서 제거 후 남은 개수 확인`() {
        val deck = CardDeck()

        deck.pickCard()

        assertThat(deck.cards.size).isEqualTo(51)
    }

    @Test
    fun `더이상 뽑을 카드가 없을때 예외 처리 확인`() {
        val deck = CardDeck()

        repeat(CARD_DECK_SIZE) {
            deck.pickCard()
        }

        assertThrows<IllegalArgumentException> { deck.pickCard() }
    }

    companion object {
        private const val CARD_DECK_SIZE = 52
    }
}
