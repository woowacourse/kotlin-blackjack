package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `소지한 카드가 5, 3이면 합은 8이다`() {
        // given
        val cards = Cards(
            listOf(
                Card(CardShape.CLUBS, CardValue.FIVE),
                Card(CardShape.DIAMONDS, CardValue.THREE),
            )
        )

        // when
        val actual = cards.actualCardValueSum()

        // then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `소지한 카드가 A, 8이면 합은 19이다`() {
        // given
        val cards = Cards(
            listOf(
                Card(CardShape.CLUBS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT),
            )
        )

        // when
        val actual = cards.actualCardValueSum()

        // then
        assertThat(actual).isEqualTo(19)
    }

    @Test
    fun `소지한 카드가 A, 5, J이면 합은 16이다`() {
        // given
        val cards = Cards(
            listOf(
                Card(CardShape.CLUBS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.FIVE),
                Card(CardShape.DIAMONDS, CardValue.JACK)
            )
        )

        // when
        val actual = cards.actualCardValueSum()

        // then
        assertThat(actual).isEqualTo(16)
    }

    @Test
    fun `소지한 카드가 A, A, 9이면 합은 21이다`() {
        // given
        val cards = Cards(
            listOf(
                Card(CardShape.CLUBS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.NINE)
            )
        )

        // when
        val actual = cards.actualCardValueSum()

        // then
        assertThat(actual).isEqualTo(21)
    }
}
