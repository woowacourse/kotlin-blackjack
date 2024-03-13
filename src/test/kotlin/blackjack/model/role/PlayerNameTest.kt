package blackjack.model.role

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerNameTest {
    @ParameterizedTest
    @ValueSource(strings = ["A", "심지해나팡태"])
    fun `이름이 2자 미만이거나 5자 초과이면 예외 발생`(input: String) {
        assertThatThrownBy {
            PlayerName(input)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이름은 2자 이상 5자 이하여야 합니다.")
    }

    @Test
    fun `이름이 2자 이상이고 5자 이하이면 통과`() {
        assertDoesNotThrow {
            PlayerName("해나")
        }
    }
}
