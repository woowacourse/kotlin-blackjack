package domain.participant

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class NameTest {
    @Test
    fun `이름은 문자열이 아닌 값이 들어가면 에러가 발생한다`() {
        assertThrows<IllegalStateException> { Name("Mendel11") }
    }

    @Test
    fun `이름에 문자열만 존재하면 이름 객체가 생성된다`() {
        assertDoesNotThrow { Name("MENDELJ가") }
    }
}
