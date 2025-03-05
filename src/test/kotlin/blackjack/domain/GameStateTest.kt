package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameStateTest {
    @Test
    fun `스코어가 21이라면 블랙잭 상태를 반환한다`() {
        val score = 21
        val expected = GameState.BLACKJACK

        GameState.from(score) shouldBe expected
    }

    @Test
    fun `스코어가 21 미만이라면 Hit 상태를 반환한다`() {
        val score = 15
        val expected = GameState.HIT

        GameState.from(score) shouldBe expected
    }

    @Test
    fun `스코어가 21 초과라면 BUST 상태를 반환한다`() {
        val score = 27
        val expected = GameState.BUST

        GameState.from(score) shouldBe expected
    }

    @Test
    fun `상태가 STAY라면 true를 반환한다`() {
        val state = GameState.STAY
        val expected = true

        GameState.checkState(state) shouldBe expected
    }

    @Test
    fun `상태가 BUST라면 true를 반환한다`() {
        val state = GameState.BUST
        val expected = true

        GameState.checkState(state) shouldBe expected
    }

    @Test
    fun `상태가 HIT이라면 false를 반환한다`() {
        val state = GameState.HIT
        val expected = false

        GameState.checkState(state) shouldBe expected
    }

    @Test
    fun `상태가 BLACKJACK이라면 false를 반환한다`() {
        val state = GameState.BLACKJACK
        val expected = false

        GameState.checkState(state) shouldBe expected
    }
}
