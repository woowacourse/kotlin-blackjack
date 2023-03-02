package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserNameContainerTest {

    @Test
    fun `두 플레이어의 이름은 '산군, 산군' 인 경우, 예외가 발생한다`() {
        // given
        val names = listOf("산군", "산군")

        // then, when
        assertThrows<IllegalArgumentException> {
            UserNameContainer(names)
        }
    }

    @Test
    fun `플레이어의 이름은 ' ' 이다`() {
        TODO("Not yet implemented")
    }
}
