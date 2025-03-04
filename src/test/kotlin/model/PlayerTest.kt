package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `플레이어의 이름은 공백일 수 없다`() {
        assertThrows<IllegalArgumentException> { Player("") }
    }
}
