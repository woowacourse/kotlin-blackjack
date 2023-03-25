package domain.tools

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import view.tools.UserNameContainer

class UserNameContainerTest {

    @Test
    fun `두 유저의 이름은 '산군, 산군' 인 경우, 예외가 발생한다`() {
        // given
        val names = listOf("산군", "산군")

        // then, when
        assertThrows<IllegalArgumentException>("[ERROR] 유저의 이름이 중복되었습니다.") {
            UserNameContainer(names)
        }
    }

    @Test
    fun `유저의 이름이 공백인 경우 예외가 발생한다`() {
        // given
        val names = listOf("", "해시")

        // then, when
        assertThrows<IllegalArgumentException>("[ERROR] 유저의 이름이 비어있습니다.") {
            UserNameContainer(names)
        }
    }
}
