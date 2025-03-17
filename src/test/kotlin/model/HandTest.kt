package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.TestCards

class HandTest {
    @Test
    fun `플레이어나 딜러가 카드를 뽑을 때 마다 추가할 수 있다`() {
        val initialCards: List<Card> =
            listOf(
                TestCards.CLUB_ACE,
                TestCards.DIAMOND_TWO,
            )

        val drawnCard =
            listOf(
                TestCards.DIAMOND_ACE,
                TestCards.HEART_TWO,
            )

        val hand = Hand(initialCards)
        hand.addCards(drawnCard)

        assertThat(hand.handCards).containsExactly(
            TestCards.CLUB_ACE,
            TestCards.DIAMOND_TWO,
            TestCards.DIAMOND_ACE,
            TestCards.HEART_TWO,
        )
    }

    @Test
    fun `플레이어나 딜러가 가진 카드의 점수를 확인할 수 있다`() {
        val initialCards: List<Card> =
            listOf(
                TestCards.CLUB_THREE,
                TestCards.DIAMOND_TWO,
            )
        val updatedHandScore = Hand(initialCards).getScore()

        assertThat(updatedHandScore).isEqualTo(5)
    }

    @Test
    fun `플레이어나 딜러가 가진 카드의 개수를 확인할 수 있다`() {
        val initialCards: List<Card> =
            listOf(
                TestCards.CLUB_THREE,
                TestCards.DIAMOND_TWO,
            )
        val updatedHandCount = Hand(initialCards).getCardsCount()

        assertThat(updatedHandCount).isEqualTo(2)
    }

    @Test
    fun `보유한 카드의 점수를 계산한다`() {
        val cards = listOf(TestCards.CLUB_SIX, TestCards.HEART_SEVEN)

        val totalScore = Hand(cards).getTotalScore()

        assertEquals(totalScore, 13)
    }

    @Test
    fun `ACE가 포함된 카드를 계산할 수 있다`() {
        val cards = listOf(TestCards.CLUB_QUEEN, TestCards.HEART_NINE, TestCards.HEART_ACE)

        val totalScore = Hand(cards).getTotalScore()

        assertEquals(totalScore, 20)
    }

    @Test
    fun `ACE가 여러개 포함되었을때의 값을 계산할 수 있다`() {
        val cards =
            listOf(
                TestCards.CLUB_ACE,
                TestCards.HEART_ACE,
                TestCards.SPADE_ACE,
                TestCards.SPADE_TEN,
                TestCards.SPADE_EIGHT,
            )

        val totalScore = Hand(cards).getTotalScore()

        assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 20일 때 ACE는 1이다`() {
        val cards = listOf(TestCards.CLUB_ACE, TestCards.SPADE_TEN, TestCards.HEART_TEN)

        val totalScore = Hand(cards).getTotalScore()

        assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 10일 때 ACE는 11이다`() {
        val cards = listOf(TestCards.CLUB_ACE, TestCards.SPADE_FIVE, TestCards.HEART_FIVE)

        val totalScore = Hand(cards).getTotalScore()

        assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE가 포함되었을 때 18임을 확인할 수 있다`() {
        val cards = listOf(TestCards.CLUB_ACE, TestCards.SPADE_ACE, TestCards.SPADE_ACE, TestCards.HEART_FIVE)

        val totalScore = Hand(cards).getTotalScore()

        assertEquals(totalScore, 18)
    }

    @Test
    fun `ACE가 포함되었을 때 21임을 확인할 수 있다`() {
        val cards1 = listOf(TestCards.CLUB_ACE, TestCards.SPADE_ACE, TestCards.HEART_NINE)
        val cards2 = listOf(TestCards.CLUB_ACE, TestCards.HEART_KING)

        val totalScore1 = Hand(cards1).getTotalScore()
        val totalScore2 = Hand(cards2).getTotalScore()

        assertEquals(totalScore1, 21)
        assertEquals(totalScore2, 21)
    }
}
