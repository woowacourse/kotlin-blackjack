package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    private val aceHeart = Card(Shape.Heart, CardNumber.Ace)
    private val aceSpade = Card(Shape.Spade, CardNumber.Ace)
    private val sixHeart = Card(Shape.Heart, CardNumber.Six)
    private val nineSpade = Card(Shape.Spade, CardNumber.Nine)
    private val kingSpade = Card(Shape.Spade, CardNumber.King)

    @Test
    fun `21점보다 숫자가 크면 Bust상태이다`() {
        // given
        val cards = mutableListOf(kingSpade, nineSpade, sixHeart)
        val hand = Hand(cards)
        // when
        val actual = hand.isBust()
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `21점보다 숫자가 작으면 Bust상태가 아니다`() {
        // given
        val cards = mutableListOf(kingSpade, nineSpade)
        val hand = Hand(cards)
        // when
        val actual = hand.isBust()
        // then
        assertThat(actual).isFalse()
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

    @Test
    fun `카드가 2장이고 총합이 21이면 블랙잭이다`() {
        // given
        val cards = mutableListOf(aceHeart, kingSpade)
        val hand = Hand(cards)
        // when
        val actual = hand.isBlackjack()
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `카드가 2장이고 총합이 21이 아닌 경우 블랙잭이 아니다`() {
        // given
        val cards = mutableListOf(aceHeart)
        val hand = Hand(cards)
        // when
        val actual = hand.isBlackjack()
        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `카드가 2장이 아니고 총합이 21인 경우 블랙잭이 아니다`() {
        // given
        val cards = mutableListOf(aceHeart, aceSpade, nineSpade, kingSpade)
        val hand = Hand(cards)
        // when
        val actual = hand.isBlackjack()
        // then
        assertThat(actual).isFalse()
    }
}
