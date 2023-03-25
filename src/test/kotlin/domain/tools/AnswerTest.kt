package domain.tools

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import view.tools.Answer

class AnswerTest {
    @ParameterizedTest
    @ValueSource(strings = ["q", "1", "!"])
    fun `답변인 y, n가 아닌 경우 예외가 발생한다`(answer: String) {
        // when, then
        assertThrows<IllegalArgumentException>("[ERROR] 올바른 문자를 입력해주세요") {
            Answer(answer)
        }
    }
}
