package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `원하는 개수 만큼 카드를 뽑을 수 있다`() {
        val cards = CardsGenerator().generateCards().allCards
        val drawCards = Cards(cards).getInitialCards()

        assertThat(drawCards.getCardsCount()).isEqualTo(2)
    }

    @Test
    fun `보유한 카드의 개수를 반환할 수 있다`() {
        val cards = cardOf(
            Card(CardRank.ACE, Shape.CLUB),
            Card(CardRank.ACE, Shape.SPADE)
        )

        assertThat(cards.getCardsCount()).isEqualTo(2)
    }

    @Test
    fun `보유한 카드의 점수들을 확인할 수 있다`() {
        val cards = cardOf(
            Card(CardRank.SIX, Shape.CLUB),
            Card(CardRank.NINE, Shape.SPADE)
        )

        val scoreLists = listOf(6, 9)

        assertThat(cards.getCardScores()).isEqualTo(scoreLists)
    }

    @Test
    fun `지정된 개수만큼 카드를 뽑은 후 남은 카드를 반환한다`() {
        val cards = CardsGenerator().generateCards()
        cards.getInitialCards()

        assertThat(cards.getCardsCount()).isEqualTo(50)
    }

    @Test
    fun `중복된 카드는 존재할 수 없다`() {
        assertThrows<IllegalArgumentException> {
            cardOf(
                Card(CardRank.ACE, Shape.CLUB),
                Card(CardRank.ACE, Shape.CLUB)
            )
        }
    }

    @Test
    fun `카드들 중에 ACE 카드의 개수를 반환한다`() {
        val cards = cardOf(
            Card(CardRank.ACE, Shape.CLUB),
            Card(CardRank.ACE, Shape.SPADE),
            Card(CardRank.SIX, Shape.SPADE)
        )
        val expected = 2
        val result = cards.aceCount()

        Assertions.assertEquals(expected, result)
    }

    companion object {
        fun cardOf(vararg card: Card): Cards {
            return Cards(card.toList())
        }
    }
}

