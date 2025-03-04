package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `플레이어의 이름은 공백일 수 없다`() {
        assertThrows<IllegalArgumentException> { Player("", Cards(mutableListOf())) }
    }

    @Test
    fun `플레이어는 게임을 시작하기 전 0장의 카드를 갖는다`() {
        assertDoesNotThrow { Player("", Cards(mutableListOf())) }
    }
}
