package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 한 장 뽑으면 덱에 있는 카드는 51장이다`() {
        //given
        val deck = Deck()
        val cards = deck.cards

        //when
        deck.pick()

        //then
        assertThat(cards.size).isEqualTo(51)
    }

    @Test
    fun `카드를 한 장 뽑으면 남은 덱에 뽑은 해당 카드는 포함되어 있지 않다`() {
        //given
        val deck = Deck()
        val cards = deck.cards

        //when
        val removedCard = deck.pick()

        //then
        assertThat(cards.contains(removedCard)).isFalse()
    }
}
