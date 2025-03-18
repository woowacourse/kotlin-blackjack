package blackjack.domain.card

import blackjack.fixture.ACE_CLUB
import blackjack.fixture.ACE_SPADE
import blackjack.fixture.NINE_SPADE
import blackjack.fixture.QUEEN_CLUB
import blackjack.fixture.QUEEN_SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `빈 핸드에 카드를 한 장 추가하면 카드는 총 한 장이다`() {
        val hand = Hand.of(ACE_SPADE)
        assertThat(hand.cards.size).isEqualTo(1)
    }

    @Test
    fun `Ace 한 장과 Queen 한 장이 있으면 점수는 21이다`() {
        // given
        val hand = Hand.of(ACE_SPADE, QUEEN_SPADE)

        // when
        val score = hand.score()

        // then
        assertThat(score.score).isEqualTo(21)
    }

    @Test
    fun `Ace 두 장과 9 한 장이 있으면 점수는 21이다`() {
        // given
        val hand = Hand.of(ACE_SPADE, ACE_CLUB, NINE_SPADE)

        // when
        val score = hand.score()

        // then
        assertThat(score.score).isEqualTo(21)
    }

    @Test
    fun `Ace 한 장과 Queen 두 장이 있으면 점수는 21이다`() {
        // given
        val hand = Hand.of(ACE_SPADE, QUEEN_SPADE, QUEEN_CLUB)

        // when
        val score = hand.score()

        // then
        assertThat(score.score).isEqualTo(21)
    }

    private fun Hand.Companion.of(vararg cards: Card): Hand = Hand().apply { cards.forEach { this.addCard(it) } }
}
