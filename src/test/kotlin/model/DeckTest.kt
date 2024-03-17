package model

import fixture.SPADE_ACE
import fixture.SPADE_TWO
import model.card.TestDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에서 pop()을 할 경우 첫번째 위치의 카드를 반환한다`() {
        val deck = TestDeck(mutableListOf(SPADE_ACE, SPADE_TWO))
        val card = deck.pop()
        assertThat(card).isEqualTo(SPADE_ACE)
    }

    @Test
    fun `덱에서 pop()을 할 경우 deck 의 사이즈는 1 감소한다`() {
        val deck = TestDeck(mutableListOf(SPADE_ACE, SPADE_TWO))
        val size = deck.cards.size
        deck.pop()
        assertThat(deck.cards.size).isEqualTo(size - 1)
    }
}
