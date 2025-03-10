package blackjack.domain

import blackjack.domain.MultiValueRank.AceRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 랭크와 수트로 구성된다`() {
        val card = Card(rank = AceRank, suit = Suit.SPADE)
        assertThat(card.rank is Rank).isTrue()
        assertThat(card.suit is Suit).isTrue()
    }
}
