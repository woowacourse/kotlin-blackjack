package blackjack.domain.hand

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.hand.Hand
import blackjack.model.hand.HandState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setup() {
        hand = Hand()
    }

    @Test
    fun `카드를 추가하면 cards 리스트에 반영된다`() {
        // given
        val card1 = Card(CardRank.FIVE, CardSuit.HEART)
        val card2 = Card(CardRank.KING, CardSuit.SPADE)

        // when
        hand.addAll(listOf(card1, card2))

        // then
        assertEquals(2, hand.cards.size)
        assertTrue(hand.cards.containsAll(listOf(card1, card2)))
    }

    @Test
    fun `카드를 추가하면 상태가 올바르게 변경된다`() {
        // given
        val card1 = Card(CardRank.ACE, CardSuit.HEART)
        val card2 = Card(CardRank.KING, CardSuit.SPADE)

        // when
        hand.addAll(listOf(card1, card2))

        // then
        assertEquals(HandState.BLACKJACK, hand.state)
    }

    @Test
    fun `점수를 올바르게 계산한다`() {
        // given
        val card1 = Card(CardRank.TEN, CardSuit.HEART)
        val card2 = Card(CardRank.FOUR, CardSuit.SPADE)

        // when
        hand.addAll(listOf(card1, card2))

        // then
        assertEquals(14, hand.score())
    }

    @Test
    fun `ACE가 포함된 경우 softScore가 적용된다`() {
        // given
        val card1 = Card(CardRank.ACE, CardSuit.HEART)
        val card2 = Card(CardRank.NINE, CardSuit.SPADE)

        // when
        hand.addAll(listOf(card1, card2))

        // then
        assertEquals(20, hand.score())
    }

    @Test
    fun `점수가 21을 초과하면 BUST 상태가 된다`() {
        // given
        val card1 = Card(CardRank.KING, CardSuit.HEART)
        val card2 = Card(CardRank.QUEEN, CardSuit.SPADE)
        val card3 = Card(CardRank.TWO, CardSuit.CLUB)

        // when
        hand.addAll(listOf(card1, card2, card3))

        // then
        assertEquals(HandState.BUST, hand.state)
        assertTrue(hand.score() > 21)
    }
}
