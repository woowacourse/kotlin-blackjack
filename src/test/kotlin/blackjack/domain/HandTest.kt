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

class HandTest {
    @Test
    fun `뽑은 카드를 핸드에 추가한다`() {
        // given
        val hand = Hand(listOf(Card(JACK,CLUB),Card(QUEEN,CLUB)))
        val card = Card(ACE, CLUB)

        // when
        hand.add(card)

        // then
        assertTrue(hand.cards.contains(card))
    }

    @Test
    fun `핸드에 2클로버 한 장,잭을 가지고 있으면 12점을 반환한다`() {
        // given
        val expectedScore = 12
        val hand = Hand(listOf(Card(JACK,CLUB),Card(TWO, CLUB)))

        // when
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(expectedScore)
    }

    @Test
    fun `핸드에 에이스를 포함한 값이 21이 넘으면 에이스를 1로 바꾼다`() {
        // given
        val expectedScore = 12
        val hand = Hand(listOf(Card(ACE,CLUB),Card(ACE,CLUB)))

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
        val hand = Hand(listOf(card1,card2))

        // when
        hand.add(card3)

        // then
        assertThat(hand.isBust()).isTrue()
    }

    @Test
    fun `ACE 1장, Q 1장, 9 1장을 가지고 있으면 20점을 반환한다`() {
        // given
        val card1 = Card(ACE, CLUB)
        val card2 = Card(QUEEN, CLUB)
        val hand = Hand(listOf(card1,card2))
        val card3 = Card(NINE, CLUB)

        //when
        hand.add(card3)
        val expectedScore = 20

        // then
        assertThat(hand.score()).isEqualTo(expectedScore)
    }
}
