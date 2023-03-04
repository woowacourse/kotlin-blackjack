package domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AnswerTest {
    @ParameterizedTest
    @ValueSource(strings = ["q", "1", "!"])
    fun `답변인 y, n가 아닌 경우 예외가 발생한다`(answer: String) {
        // when, then
        assertThrows<IllegalArgumentException>("[ERROR] 올바른 입력이 아닙니다.") {
            Answer(answer)
        }
    }
}
