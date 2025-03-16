package model

import org.assertj.core.api.Assertions.assertThat
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
}
