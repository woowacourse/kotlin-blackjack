package entity

import entity.users.Name
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NameTest {
    @Test
    fun `이름이 0글자 작성되면 예외 발생`() {
        val message = assertThrows<IllegalArgumentException> {
            Name("")
        }.message

        Assertions.assertThat(message).isEqualTo("이름은 1글자 이상 작성되어야 합니다.")
    }
}
