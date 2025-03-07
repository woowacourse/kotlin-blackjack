package blackjack.domain.enums

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class UserChoiceTest {
    @Test
    fun `입력이 y일 때 HIT을 반환해야 한다`() {
        val result = UserChoice.from("y")
        assertEquals(UserChoice.HIT, result)
    }

    @Test
    fun `입력이 n일 때 STAY을 반환해야 한다`() {
        val result = UserChoice.from("n")
        assertEquals(UserChoice.STAY, result)
    }

    @Test
    fun `유효하지 않은 입력일 때 IllegalArgumentException을 던져야 한다`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                UserChoice.from("invalid")
            }
        assertNotNull(exception)
    }
}
