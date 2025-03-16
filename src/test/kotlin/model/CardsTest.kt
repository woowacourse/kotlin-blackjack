package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import util.TestCards
import util.TestShuffler

class CardsTest {
    @Test
    fun `중복된 카드가 포함되면 예외가 발생한다`() {
        val duplicateCards =
            listOf(
                TestCards.CLUB_ACE,
                TestCards.CLUB_ACE,
            )

        assertThrows<IllegalArgumentException> {
            Cards(duplicateCards)
        }
    }

    @Test
    fun `남아있는 카드보다 더 많은 카드를 뽑으면 예외가 발생한다`() {
        val cards = Cards(listOf(TestCards.CLUB_ACE), TestShuffler())

        assertThrows<IllegalArgumentException> {cards.drawCards(2) }
    }

    @Test
    fun `셔플 없이 카드를 뽑으면 순서가 유지된다`() {
        val cards = Cards(
            listOf(TestCards.CLUB_ACE, TestCards.SPADE_KING, TestCards.HEART_QUEEN),
            TestShuffler()
        )

        val drawnCards = cards.drawCards(2)

        assertThat(drawnCards).containsExactly(TestCards.CLUB_ACE, TestCards.SPADE_KING)
    }
}
