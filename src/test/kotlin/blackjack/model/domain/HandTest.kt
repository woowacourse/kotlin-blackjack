package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand
import blackjack.model.domain.card.Status
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

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
    fun `Ace가 두장일 때 하나는 11로 계산된다`() {
        // given
        val cards = mutableListOf(Card.from("AceHeart"), Card.from("AceSpade"))
        val hand = Hand(cards)
        // when
        val actual = hand.getSumNumber()
        val expected = 12
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("faceCards")
    fun `Queen과 King과 Jack은 10으로 계산된다`(cards: MutableList<Card>) {
        // given
        val hand = Hand(cards)
        // when
        val actual = hand.getSumNumber()
        val expected = 20
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
    fun `Bust이면 상태가 bust가 된다`() {
        // given
        val cards = mutableListOf(Card.from("TenHeart"), Card.from("QueenSpade"), Card.from("FiveSpade"))
        val hand = Hand(cards)
        // when
        hand.isBust()
        val actual = hand.status
        val expected = Status.BUST
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Blackjack이면 상태가 Blackjack이 된다`() {
        // given
        val cards = mutableListOf(Card.from("TenHeart"), Card.from("AceSpade"))
        val hand = Hand(cards)
        // when
        hand.isBlackJack()
        val actual = hand.status
        val expected = Status.BLACKJACK
        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun faceCards() =
            mutableListOf(
                listOf(Card.from("JackHeart"), Card.from("QueenSpade")),
                listOf(Card.from("QueenHeart"), Card.from("KingSpade")),
                listOf(Card.from("JackHeart"), Card.from("KingSpade")),
            )
    }
}
