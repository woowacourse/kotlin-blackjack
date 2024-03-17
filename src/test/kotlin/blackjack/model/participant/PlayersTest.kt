package blackjack.model.participant

import blackjack.model.DEFAULT_BATTING_AMOUNT
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

private fun battingAmounts(size: Int) = List(size) { DEFAULT_BATTING_AMOUNT }

class PlayersTest {
    @Test
    fun `플레이어명이 중복된 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players.from(listOf("olive", "olive"), battingAmounts(2))
        }
    }

    @Test
    fun `플레이어 수가 2명 미만인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players.from(listOf("a"), battingAmounts(1))
        }
    }

    @Test
    fun `플레이어 수가 8명 초과인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players.from(listOf("a", "b", "c", "d", "e", "f", "g", "h", "i"), battingAmounts(9))
        }
    }

    @Test
    fun `플레이어명 크기와 배팅 금액 크기가 다른 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players.from(listOf("olive", "abc", "defgh"), battingAmounts(2))
        }
    }

    @Test
    fun `플레이어명, 플레이어 수, 배팅 금액이 모두 올바른 경우 예외가 발생하지 않는다`() {
        assertDoesNotThrow {
            Players.from(listOf("olive", "abc", "defgh"), battingAmounts(3))
        }
    }
}
