package blackjack.domain

import blackjack.domain.enums.Rank
import blackjack.domain.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `Ace 한 장과 Queen 한 장이 있으면 점수는 21이다`() {
        // given
        val aceCard = Card(Rank.ACE, Suit.SPADE)
        val queenCard = Card(Rank.QUEEN, Suit.SPADE)
        val hand = Hand(listOf(aceCard, queenCard))

        // when
        val score = hand.calculateScore()

        // then
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `Ace 두 장과 9 한 장이 있으면 점수는 21이다`() {
        // given
        val aceSpade = Card(Rank.ACE, Suit.SPADE)
        val aceDiamond = Card(Rank.ACE, Suit.DIAMOND)
        val nineSpade = Card(Rank.NINE, Suit.SPADE)
        val hand = Hand(listOf(aceSpade, aceDiamond, nineSpade))

        // when
        val score = hand.calculateScore()

        // then
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `Ace 한 장과 Queen 두 장이 있으면 점수는 21이다`() {
        // given
        val aceSpade = Card(Rank.ACE, Suit.SPADE)
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenDiamond = Card(Rank.QUEEN, Suit.DIAMOND)
        val hand = Hand(listOf(aceSpade, queenSpade, queenDiamond))

        // when
        val score = hand.calculateScore()

        // then
        assertThat(score).isEqualTo(21)
    }
}
