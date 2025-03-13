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
}
