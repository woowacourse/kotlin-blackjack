package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드를 뽑으면 마지막 카드가 뽑힌다`() {
        // given
        val deck = Deck(listOf(Card(Rank.TWO, Suit.HEART), Card(Rank.ACE, Suit.HEART)))

        // when
        val pickedCard = deck.pick()

        // then
        assertThat(pickedCard).isEqualTo(Card(Rank.ACE, Suit.HEART))
    }
}
