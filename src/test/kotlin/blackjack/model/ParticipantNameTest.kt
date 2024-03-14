import blackjack.model.ParticipantName
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ParticipantNameTest {
    @Test
    fun `이름은 공백이 될 수 없다`() {
        val unValidName = ""
        assertThrows(IllegalArgumentException::class.java) {
            ParticipantName(unValidName)
        }
    }
}
