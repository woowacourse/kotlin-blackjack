package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val card = Card(Shape.HEART, CardValue.EIGHT)
        val userCards = Cards(mutableListOf())
        userCards.pickCard(card)
        assertThat(userCards.cards).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 값의 합을 반환한다`() {
        val cards =
            Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.ACE)))
        assertThat(cards.calculateCardSum()).isEqualTo(21)
    }

    @Test
    fun `A가 2개 포함되어 있는 카드 값의 합을 반환한다`() {
        val cards =
            Cards(mutableListOf(Card(Shape.SPADE, CardValue.ACE), Card(Shape.HEART, CardValue.ACE)))
        assertThat(cards.calculateCardSum()).isEqualTo(12)
    }

    @Test
    fun `10 초과의 값을 갖고 있는 상태에 A가 포함될 경우의 카드 값 합을 반환한다`() {
        val cards =
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.HEART, CardValue.THREE),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )
        assertThat(cards.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `A가 하나 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val cards =
            Cards(
                mutableListOf(
                    Card(Shape.HEART, CardValue.ACE),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.TWO)
                )
            )
        assertThat(cards.calculateCardSum()).isEqualTo(13)
    }

    @Test
    fun `A와 다른 값을 교차로 갖는 카드 값의 합을 반환한다`() {
        val cards =
            Cards(
                mutableListOf(
                    Card(Shape.HEART, CardValue.THREE),
                    Card(Shape.SPADE, CardValue.ACE),
                    Card(Shape.SPADE, CardValue.FOUR),
                    Card(Shape.DIAMOND, CardValue.ACE)
                )
            )
        assertThat(cards.calculateCardSum()).isEqualTo(19)
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘었을 경우 true를 반환한다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK)
            )
        )
        assertThat(cards.checkBurst()).isTrue
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘지 않았을 경우 false를 반환한다`() {
        val cards = Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK)))
        assertThat(cards.checkBurst()).isFalse
    }
}
