package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import util.TestCardGenerator
import util.TestCards

class CardsTest {
    private val testCardsGenerator = TestCardGenerator()
    private val cards = Cards(testCardsGenerator.generateCards().allCards)

    @Test
    fun `카드를 정상적으로 뽑을 수 있다`() {
        val drawnCards = cards.drawCards(2)

        assertThat(drawnCards).hasSize(2)
        assertThat(cards.allCards).hasSize(2)
    }

    @Test
    fun `남아있는 카드보다 많은 카드를 뽑으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            cards.drawCards(5)
        }
    }

    @Test
    fun `덱이 다 소진되면 자동으로 새로운 덱이 생성된다`() {
        cards.drawCards(4)
        assertThat(cards.allCards).isEmpty()

        cards.drawCards(1)
        assertThat(cards.allCards).isNotEmpty()
    }

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
    fun `카드가 원하는 순서대로 뽑히는지 확인`() {
        val cards = Cards(TestCardGenerator().generateCards().allCards)

        val firstDraw = cards.drawCards(2)
        assertThat(firstDraw).containsExactly(TestCards.CLUB_ACE, TestCards.SPADE_KING)

        val secondDraw = cards.drawCards(2)
        assertThat(secondDraw).containsExactly(TestCards.HEART_QUEEN, TestCards.DIAMOND_JACK)
    }
}
