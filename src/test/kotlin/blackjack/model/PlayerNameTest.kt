package blackjack.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerNameTest {
    @ValueSource(strings = ["OLIVE", "olives", "seogii", "chaechae", "123", "올리브", "서기!"])
    @ParameterizedTest
    fun `이름이 다섯 글자 이하 소문자가 아닐 경우 예외가 발생한다`(name: String) {
        assertThrows<IllegalArgumentException> { PlayerName(name) }
    }

    @ValueSource(strings = ["chae", "olive", "seogi"])
    @ParameterizedTest
    fun `이름이 다섯 글자 이하 소문자일 경우 예외가 발생하지 않는다`(name: String) {
        assertDoesNotThrow { PlayerName(name) }
    }
}
