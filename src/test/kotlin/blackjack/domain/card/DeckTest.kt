package blackjack.domain.card

import blackjack.fixture.ACE_SPADE
import blackjack.fixture.KING_SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에 카드 한 장이 남아있다면 해당 카드가 나온다`() {
        val deck = Deck(listOf(ACE_SPADE))
        val card = deck.pick()
        assertThat(card).isEqualTo(ACE_SPADE)
    }

    @Test
    fun `덱에서 카드 한 장을 뽑으면 마지막 카드가 나온다`() {
        val deck = Deck(listOf(KING_SPADE, ACE_SPADE))
        val card = deck.pick()
        assertThat(card).isEqualTo(ACE_SPADE)
    }
}
