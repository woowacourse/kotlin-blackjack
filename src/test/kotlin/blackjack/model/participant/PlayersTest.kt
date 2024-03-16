package blackjack.model.participant

import blackjack.model.DEFAULT_BATTING_AMOUNT
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayersTest {
    @Test
    fun `플레이어명이 중복된 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players.from(listOf("olive", "olive"), battingAmounts(2))
        }
    }

    @MethodSource("플레이어 수 제한 범위 테스트 데이터")
    @ParameterizedTest
    fun `플레이어 수가 제한 범위를 넘기는 경우 예외가 발생한다`(playersName: List<String>) {
        assertThrows<IllegalArgumentException> {
            Players.from(playersName, battingAmounts(playersName.size))
        }
    }

    @Test
    fun `플레이어명, 플레이어 수가 올바른 경우 예외가 발생하지 않는다`() {
        assertDoesNotThrow {
            Players.from(listOf("olive", "abc", "defgh"), battingAmounts(3))
        }
    }

    companion object {
        fun battingAmounts(size: Int) = List(size) { DEFAULT_BATTING_AMOUNT }

        @JvmStatic
        fun `플레이어 수 제한 범위 테스트 데이터`() =
            listOf(
                Arguments.of(listOf("a")),
                Arguments.of(listOf("a", "b", "c", "d", "e", "f", "g", "h", "i")),
            )
    }
}
