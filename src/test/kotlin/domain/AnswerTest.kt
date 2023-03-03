package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AnswerTest {
    @Test
    fun `y를 입력 받으면 Yes을 반환한다`() {
        val actual = Answer.of("y")
        val expected = Answer.YES
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `n을 입력 받으면 NO를 반환한다`() {
        val actual = Answer.of("n")
        val expected = Answer.NO
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `y와 n을 제외한 값을 입력 받으면 예외를 발생한다`() {
        assertThrows<IllegalArgumentException> { Answer.of("c") }
    }
}
