package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Rank.AceRank
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 랭크와 수트로 구성된다`() {
        val card = Card.of(AceRank, Suit.SPADE)
        assertThat(card.rank is Rank).isTrue()
        assertThat(card.suit is Suit).isTrue()
    }
}
