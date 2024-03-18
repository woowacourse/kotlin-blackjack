import blackjack.model.ParticipantBetAmount
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ParticipantBetAmountTest {
    @Test
    fun `배팅 금액은 1원이상 이여야한다`() {
        val unValidBetAmount = 0
        assertThrows(IllegalArgumentException::class.java) {
            ParticipantBetAmount(unValidBetAmount)
        }
    }
}
