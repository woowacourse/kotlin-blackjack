package blackjack.model

import model.Card
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드를 생성한다`() {
        val card = Card(Rank.ACE, Suit.CLOVER)
        assertThat(card.rank).isEqualTo(Rank.ACE)
        assertThat(card.suit).isEqualTo(Suit.CLOVER)
    }
}
