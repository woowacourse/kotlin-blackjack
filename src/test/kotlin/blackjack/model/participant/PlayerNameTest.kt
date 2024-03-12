package blackjack.model.participant

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerNameTest {
    @ValueSource(strings = ["", "olives", "byeori"])
    @ParameterizedTest
    fun `이름이 1~5글자 사이가 아닐 경우 예외가 발생한다`(name: String) {
        assertThrows<IllegalArgumentException> { PlayerName(name) }
    }

    @ValueSource(strings = ["OLIVE", "oLive", "ㅇlive", "올리브", "서기!"])
    @ParameterizedTest
    fun `이름이 소문자가 아닐 경우 예외가 발생한다`(name: String) {
        assertThrows<IllegalArgumentException> { PlayerName(name) }
    }

    @ValueSource(strings = ["chae", "olive", "seogi"])
    @ParameterizedTest
    fun `이름이 조건에 맞는 경우 예외가 발생하지 않는다`(name: String) {
        assertDoesNotThrow { PlayerName(name) }
    }
}
