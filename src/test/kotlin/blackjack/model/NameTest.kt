package blackjack.model

import model.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NameTest {
    @Test
    fun `Name을 생성할 수 있다`() {
        assertThat(Name("jason").value).isEqualTo("jason")
    }

    @Test
    fun `Name에 한글 영어 외 문자가 들어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Name("ja-son") }
    }
}
