package blackjack.domain

import blackjack.domain.Rank.AceRank
import blackjack.domain.Rank.FaceRank
import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `갖고 있는 카드를 확인할 수 있다`() {
        val card = Card(AceRank, Suit.SPADE)
        val cards = Hand(listOf(card))
        assertThat(cards.value).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 총합을 알 수 있다`() {
        val cards =
            Hand(
                listOf(
                    Card(AceRank, Suit.SPADE),
                    Card(AceRank, Suit.HEART),
                    Card(NumberRank.FOUR, Suit.DIAMOND),
                    Card(FaceRank.JACK, Suit.CLOVER),
                ),
            )
        assertThat(cards.score).isEqualTo(16)
    }
}
