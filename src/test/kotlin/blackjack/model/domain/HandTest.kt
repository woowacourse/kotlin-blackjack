package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardNumber
import blackjack.model.domain.card.Hand
import blackjack.model.domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class HandTest {
    private val aceHeart = Card(Shape.Heart, CardNumber.Ace)
    private val aceSpade = Card(Shape.Spade, CardNumber.Ace)
    private val sixHeart = Card(Shape.Heart, CardNumber.Six)
    private val nineSpade = Card(Shape.Spade, CardNumber.Nine)
    private val kingSpade = Card(Shape.Spade, CardNumber.King)

    // given
    @ValueSource(ints = [22, 23, 24, 100])
    @ParameterizedTest
    fun `임계값 보다 숫자가 크게 되면 Bust상태를 반환한다`(input: Int) {
        // given
        val cards = mutableListOf(kingSpade, nineSpade, sixHeart)
        val hand = Hand(cards)
        // when
        val actual = hand.isBust()
        val expected = GameResult.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 숫자의 합을 계산한다`() {
        // given
        val cards = mutableListOf(sixHeart, nineSpade)
        val hand = Hand(cards)
        // when
        val actual = hand.getSumNumber()
        val expected = 15
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace가 두장일 때 하나는 11로 계산된다`() {
        // given
        val cards = mutableListOf(aceHeart, aceSpade)
        val hand = Hand(cards)
        // when
        val actual = hand.getSumNumber()
        val expected = 12
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
