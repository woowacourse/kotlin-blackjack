package blackjack.model.participant

import blackjack.model.DEFAULT_BATTING_AMOUNT
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

private fun Players(
    names: List<String>,
    amounts: List<Int> = List(names.size) { DEFAULT_BATTING_AMOUNT },
): Players {
    return Players.from(names, amounts)
}

class PlayersTest {
    @Test
    fun `플레이어명이 중복된 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players(listOf("olive", "olive"))
        }
    }

    @Test
    fun `플레이어 수가 2명 미만인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players(listOf("a"))
        }
    }

    @Test
    fun `플레이어 수가 8명 초과인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players(listOf("a", "b", "c", "d", "e", "f", "g", "h", "i"))
        }
    }

    @Test
    fun `플레이어명 크기와 배팅 금액 크기가 다른 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players(listOf("olive", "abc", "defgh"), List(2) { DEFAULT_BATTING_AMOUNT })
        }
    }

    @Test
    fun `플레이어명, 플레이어 수, 배팅 금액이 모두 올바른 경우 예외가 발생하지 않는다`() {
        assertDoesNotThrow {
            Players(listOf("olive", "abc", "defgh"))
        }
    }
}
