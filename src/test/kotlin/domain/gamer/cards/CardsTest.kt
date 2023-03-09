package domain.gamer.cards

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 값의 합을 반환한다`() {
        val cards = Cards(
            listOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))
        )
        assertThat(cards.calculateCardSum()).isEqualTo(20)
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘었을 경우 true를 반환한다`() {
        val cards = Cards(
            listOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK)
            )
        )

        assertThat(cards.checkBurst()).isTrue
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘지 않았을 경우 false를 반환한다`() {
        val cards = Cards(
            (listOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK)))
        )
        assertThat(cards.checkBurst()).isFalse
    }

    @Test
    fun `A가 2개 포함되어 있는 카드 값의 합을 반환한다`() {
        val cards = Cards(
            listOf(
                Card(Shape.SPADE, CardValue.ACE),
                Card(Shape.HEART, CardValue.ACE)
            )
        )
        assertThat(cards.calculateCardSum()).isEqualTo(12)
    }

    @Test
    fun `A가 하나 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val cards = Cards(
            listOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.HEART, CardValue.THREE),
                Card(Shape.SPADE, CardValue.ACE)
            )
        )
        assertThat(cards.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `A가 두개 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val cards = Cards(
            listOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.TWO),
                Card(Shape.HEART, CardValue.ACE),
                Card(Shape.SPADE, CardValue.ACE)
            )
        )
        assertThat(cards.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `카드가 2개이고 합이 21이면 블랙잭이다`() {
        val cards = Cards(
            listOf(
                Card(Shape.SPADE, CardValue.JACK),
                (Card(Shape.SPADE, CardValue.ACE))
            )
        )
        assertThat(cards.checkBlackjack()).isEqualTo(true)
    }

    @Test
    fun `카드가 3개이고 합이 21이면 블랙잭이 아니다`() {
        val cards = Cards(
            listOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.FIVE),
                Card(Shape.SPADE, CardValue.SIX)
            )
        )
        assertThat(cards.checkBlackjack()).isEqualTo(false)
    }

    @Test
    fun `카드가 2개이고 합이 21이 아니면 블랙잭이 아니다`() {
        val cards = Cards(
            listOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.FIVE)
            )
        )
        assertThat(cards.checkBlackjack()).isEqualTo(false)
    }
}
