package domain.person

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    @Test
    fun `플레이어 이름은 중복되면 안된다`() {
        // given
        val dealer = Dealer()
        val players =
            listOf(
                Player("베르"),
                Player("베르"),
            )

        // then
        assertThrows<IllegalArgumentException> { Participants(dealer, players) }
    }
}
