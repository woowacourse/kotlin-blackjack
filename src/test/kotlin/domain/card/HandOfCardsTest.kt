package domain.card

import domain.card.strategy.SumStrategy.getAppropriateSum
import domain.card.strategy.SumStrategy.getMinSum
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
    fun `최대한 21에 가깝고 21을 초과하지 않도록 카드 합계 계산`(number1: CardNumber, number2: CardNumber, expected: Int) {
        // given
        val handOfCards = HandOfCards(
            Card(CardShape.HEART, number1),
            Card(CardShape.HEART, number2),
        )

        // when
        val actual = handOfCards.getAppropriateSum()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @CsvSource(value = ["ACE,TEN,11", "ACE,ACE,2"])
    @ParameterizedTest
    fun `최소한으로 합계 계산(ACE는 무조건 1로 계산)`(number1: CardNumber, number2: CardNumber, expected: Int) {
        // given
        val handOfCards = HandOfCards(
            Card(CardShape.HEART, number1),
            Card(CardShape.HEART, number2),
        )

        // when
        val actual = handOfCards.getMinSum()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace카드의 수를 센다`() {
        val handOfCards = HandOfCards(
            Card(CardShape.HEART, CardNumber.ACE),
            Card(CardShape.DIAMOND, CardNumber.KING),
        )
        handOfCards.addCard(Card(CardShape.HEART, CardNumber.TWO))
        handOfCards.addCard(Card(CardShape.CLOVER, CardNumber.ACE))
        val expected = 2

        // when
        val actual = handOfCards.countAce()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace 카드를 제외한 카드들의 총합을 계산한다`() {
        // given
        val handOfCards = HandOfCards(
            Card(CardShape.HEART, CardNumber.ACE),
            Card(CardShape.DIAMOND, CardNumber.KING),
        )
        handOfCards.addCard(Card(CardShape.HEART, CardNumber.TWO))
        handOfCards.addCard(Card(CardShape.CLOVER, CardNumber.TEN))
        val expected = 22

        // when
        val actual = handOfCards.getExceptAceSum()

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
