package blackjack.domain.hand

import blackjack.model.hand.HandState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HandStateTest {
    @Test
    fun `점수가 21 초과이면 BUST 상태가 된다`() {
        // given
        val score = 22
        val cardCount = 3

        // when
        val result = HandState.from(score, cardCount)

        // then
        assertEquals(HandState.BUST, result)
    }

    @Test
    fun `점수가 21 미만이면 ALIVE 상태가 된다`() {
        // given
        val score = 20
        val cardCount = 2

        // when
        val result = HandState.from(score, cardCount)

        // then
        assertEquals(HandState.ALIVE, result)
    }

    @Test
    fun `점수가 21이고 카드 개수가 2장이라면 BLACKJACK 상태가 된다`() {
        // given
        val score = 21
        val cardCount = 2

        // when
        val result = HandState.from(score, cardCount)

        // then
        assertEquals(HandState.BLACKJACK, result)
    }

    @Test
    fun `점수가 21이지만 카드 개수가 2장이 아니면 ALIVE 상태가 된다`() {
        // given
        val score = 21
        val cardCount = 3

        // when
        val result = HandState.from(score, cardCount)

        // then
        assertEquals(HandState.ALIVE, result)
    }
}
