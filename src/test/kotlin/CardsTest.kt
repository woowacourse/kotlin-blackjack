import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardsTest {

    @Test
    fun `게임을 시작할때 카드가 두장이 아니면 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Cards(listOf(Card()))
        }
    }

    @Test
    fun `게임을 시작할때 카드가 두장이어야한다`() {
        assertDoesNotThrow {
            Cards(listOf(Card(), Card()))
        }
    }
}
