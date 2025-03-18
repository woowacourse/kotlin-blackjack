package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardRank.ACE
import blackjack.model.CardRank.JACK
import blackjack.model.CardRank.KING
import blackjack.model.CardRank.NINE
import blackjack.model.CardRank.QUEEN
import blackjack.model.CardRank.TWO
import blackjack.model.CardSuit.CLUB
import blackjack.model.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

val JACK_CLUB = Card.getCashed(JACK, CLUB)
val QUEEN_CLUB = Card.getCashed(QUEEN, CLUB)
val KING_CLUB = Card.getCashed(KING, CLUB)
val ACE_CLUB = Card.getCashed(ACE, CLUB)
val NINE_CLUB = Card.getCashed(NINE, CLUB)
val TWO_CLUB = Card.getCashed(TWO, CLUB)

class HandTest {
    @Test
    fun `뽑은 카드를 핸드에 추가한다`() {
        // given
        val hand = Hand(listOf(JACK_CLUB, QUEEN_CLUB))

        // when
        hand.add(ACE_CLUB)

        // then
        assertTrue(hand.cards.contains(ACE_CLUB))
    }

    @Test
    fun `핸드에 2클로버 한 장,잭을 가지고 있으면 12점을 반환한다`() {
        // given
        val expectedScore = 12
        val hand = Hand(listOf(JACK_CLUB, TWO_CLUB))

        // when
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(expectedScore)
    }

    @Test
    fun `핸드에 에이스를 포함한 값이 21이 넘으면 에이스를 1로 바꾼다`() {
        // given
        val expectedScore = 12
        val hand = Hand(listOf(ACE_CLUB, ACE_CLUB))

        // when
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(expectedScore)
    }

    @Test
    fun `21점이 초과하면 Bust를 반환한다`() {
        // given
        val hand = Hand(listOf(QUEEN_CLUB, KING_CLUB))

        // when
        hand.add(JACK_CLUB)

        // then
        assertThat(hand.isBust()).isTrue()
    }

    @Test
    fun `ACE 1장, Q 1장, 9 1장을 가지고 있으면 20점을 반환한다`() {
        // given
        val hand = Hand(listOf(ACE_CLUB, QUEEN_CLUB))

        // when
        hand.add(NINE_CLUB)
        val expectedScore = 20

        // then
        assertThat(hand.score()).isEqualTo(expectedScore)
    }
}
