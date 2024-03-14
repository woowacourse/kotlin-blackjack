package blackjack.model.participant

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayersTest {
    @MethodSource("플레이어 명 중복 테스트 데이터")
    @ParameterizedTest
    fun `플레이어명이 중복된 경우 예외가 발생한다`(playersName: List<String>) {
        val exception = assertThrows<IllegalArgumentException> { Players.of(playersName, listOf(1000, 1000)) }
        assertThat(exception.message).isEqualTo("플레이어명은 중복될 수 없습니다. 중복된 닉네임: ${findDuplicatedNames(playersName)}")
    }

    @MethodSource("플레이어 수 제한 범위 테스트 데이터")
    @ParameterizedTest
    fun `플레이어 수가 제한 범위를 넘기는 경우 예외가 발생한다`(playersName: List<String>) {
        val exception = assertThrows<IllegalArgumentException> { Players.of(playersName, listOf(1000, 1000)) }
        assertThat(exception.message).isEqualTo("플레이어는 2~8 사이여야 합니다. 현재 플레이어 수: ${playersName.size}")
    }

    companion object {
        @JvmStatic
        fun `플레이어 명 중복 테스트 데이터`() =
            listOf(
                Arguments.of(listOf("olive", "olive", "seogi")),
                Arguments.of(listOf("olive", "olive", "seogi", "seogi")),
            )

        @JvmStatic
        private fun `플레이어 수 제한 범위 테스트 데이터`() =
            listOf(
                Arguments.of(listOf("a")),
                Arguments.of(listOf("a", "b", "c", "d", "e", "f", "g", "h", "i")),
            )

        private fun findDuplicatedNames(playersName: List<String>): String {
            val duplicatedNames = mutableSetOf<String>()
            return playersName.filter { !duplicatedNames.add(it) }.joinToString()
        }
    }
}
