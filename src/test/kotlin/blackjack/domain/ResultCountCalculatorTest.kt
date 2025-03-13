package blackjack.domain

import blackjack.model.card.Card
import blackjack.model.card.CardRank.ACE
import blackjack.model.card.CardRank.JACK
import blackjack.model.card.CardRank.KING
import blackjack.model.card.CardRank.NINE
import blackjack.model.card.CardRank.QUEEN
import blackjack.model.card.CardRank.TWO
import blackjack.model.card.CardSuit.CLUB
import blackjack.model.participant.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultCountCalculatorTest {
    @Test
    fun `핸드에 2클로버 한 장을 가지고 있으면 2점을 반환한다`() {
        // given
        val expectedScore = 2
        val card = Card(TWO, CLUB)
        val hand = Hand(listOf(card))

        // when
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(expectedScore)
    }

    @Test
    fun `핸드에 에이스를 포함한 값이 21이 넘으면 에이스를 1로 바꾼다`() {
        // given
        val expectedScore = 2
        val card = Card(TWO, CLUB)
        val hand = Hand(listOf(card))

        // when
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(expectedScore)
    }

    @Test
    fun `21점이 초과하면 Bust를 반환한다`() {
        // given
        val card1 = Card(QUEEN, CLUB)
        val card2 = Card(KING, CLUB)
        val card3 = Card(JACK, CLUB)
        val cards = listOf(card1, card2, card3)
        val hand = Hand(cards)

        // when
        val score = hand.score()

        // then
        assertThat(hand.isBust(score)).isTrue()
    }

    @Test
    fun `ACE 1장, Q 1장, 9 1장을 가지고 있으면 20점을 반환한다`() {
        // given
        val card1 = Card(ACE, CLUB)
        val card2 = Card(QUEEN, CLUB)
        val card3 = Card(NINE, CLUB)
        val cards = listOf(card1, card2, card3)
        val hand = Hand(cards)
        val expectedScore = 20

        // when & then
        assertThat(hand.score()).isEqualTo(expectedScore)
    }
}
