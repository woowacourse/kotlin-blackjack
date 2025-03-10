package blackjack.domain

import blackjack.domain.Rank.AceRank
import blackjack.domain.Rank.FaceRank
import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    fun Hand.draw(vararg card: Card) {
        card.forEach { card ->
            draw(card)
        }
    }

    @Test
    fun `갖고 있는 카드를 확인할 수 있다`() {
        val card = Card(AceRank, Suit.SPADE)
        val hand = Hand()
        hand.draw(card)
        assertThat(hand.value).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 총합을 알 수 있다`() {
        val hand =
            Hand().apply {
                draw(
                    Card(AceRank, Suit.SPADE),
                    Card(AceRank, Suit.HEART),
                    Card(NumberRank.FOUR, Suit.DIAMOND),
                    Card(FaceRank.JACK, Suit.CLOVER),
                )
            }
        assertThat(hand.score).isEqualTo(16)
    }
}
