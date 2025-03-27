package blackjack.model.card

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `에이스가 있고 점수가 11점이하이면 Ace를 11로 판단해 계산한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE), Card(CardShape.HEART, Denomination.TEN))
        val actual = hand.score()

        val expected = 21

        org.assertj.core.api.Assertions
            .assertThat(actual)
            .isEqualTo(expected)
    }

    @Test
    fun `에이스가 없으면 끗수의 합을 계산한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.TWO), Card(CardShape.HEART, Denomination.TEN))
        val actual = hand.score()

        val expected = 12

        org.assertj.core.api.Assertions
            .assertThat(actual)
            .isEqualTo(expected)
    }
}
