package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class HandOfCardsTest {
    @Test
    fun `카드가 잘 추가되는지 확인한다`() {
        // given
        val handOfCards = HandOfCards(
            Card(CardShape.HEART, CardNumber.ACE),
            Card(CardShape.DIAMOND, CardNumber.KING),
        )
        val cardSize = handOfCards.cards.size

        // when
        handOfCards.addCard(Card(CardShape.HEART, CardNumber.KING))

        // then
        assertThat(handOfCards.cards.size).isEqualTo(cardSize + 1)
    }

    @CsvSource(value = ["ACE,TEN,21", "ACE,ACE,12"])
    @ParameterizedTest
    fun `Ace를 잘 판단하여 카드 합계를 계산하는지 테스트`(number1: CardNumber, number2: CardNumber, expected: Int) {
        // given
        val handOfCards = HandOfCards(
            Card(CardShape.HEART, number1),
            Card(CardShape.HEART, number2),
        )

        // when
        val actual = handOfCards.getTotalCardSum()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `첫 번째 카드만 보여준다`() {
        // given
        val handOfCards = HandOfCards(
            Card(CardShape.HEART, CardNumber.ACE),
            Card(CardShape.DIAMOND, CardNumber.KING),
        )
        val expected = Card(CardShape.HEART, CardNumber.ACE)

        // when
        val actual = handOfCards.showFirstCard()

        // then
        assertAll(
            { assertThat(actual.size).isEqualTo(1) },
            { assertThat(actual).isEqualTo(listOf(expected)) },
        )
    }
}
