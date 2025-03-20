package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `카드 숫자의 합을 계산한다`() {
        // given
        val cards = mutableListOf(Card.from("SixHeart"), Card.from("NineSpade"))
        val hand = Hand(cards)
        // when
        val actual = hand.getSumNumber()
        val expected = 15
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace가 11로 계산되어도 카드의 합이 21이 넘지 않으면 Ace는 11로 계산된다`() {
        // given
        val cards = mutableListOf(Card.from("AceHeart"), Card.from("AceSpade"))
        val hand = Hand(cards)
        // when
        val actual = hand.getSumNumber()
        val expected = 12
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Queen과 King과 Jack은 10으로 계산된다`() {
        // given
        val cards = mutableListOf(Card.from("JackHeart"), Card.from("QueenSpade"), Card.from("KingSpade"))
        val hand = Hand(cards)
        // when
        val actual = hand.getSumNumber()
        val expected = 30
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드를 한장 추가한다`() {
        // given
        val cards = mutableListOf(Card.from("AceHeart"), Card.from("AceSpade"))
        val hand = Hand(cards)
        // when
        hand.append(mutableListOf(Card.from("TwoHeart")))
        val actual = hand.cards
        val expected = mutableListOf(Card.from("AceHeart"), Card.from("AceSpade"), Card.from("TwoHeart"))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드들의 숫자들의 합이 21이 넘으면 bust이다`() {
        // given
        val cards = mutableListOf(Card.from("TenHeart"), Card.from("QueenSpade"), Card.from("FiveSpade"))
        val hand = Hand(cards)
        // when
        val actual = hand.isBust()
        val expected = true
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `손에 카드가 두장이고 카드 숫자이 합이 21이면 상태가 Blackjack이다`() {
        // given
        val cards = mutableListOf(Card.from("TenHeart"), Card.from("AceSpade"))
        val hand = Hand(cards)
        // when
        val actual = hand.isBlackJack()
        val expected = true
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `손에 카드가 세장 이상이고 카드 숫자이 합이 21이면 Blackjack이 아니다`() {
        // given
        val cards = mutableListOf(Card.from("FiveHeart"), Card.from("FiveSpade"), Card.from("AceSpade"))
        val hand = Hand(cards)
        // when
        val actual = hand.isBlackJack()
        val expected = false
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
