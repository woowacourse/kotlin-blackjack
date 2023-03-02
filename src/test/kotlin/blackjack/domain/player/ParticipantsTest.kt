package blackjack.domain.player

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class ParticipantsTest {

    @Test
    fun `참가자들을 생성할 수 있다`() {
        assertDoesNotThrow { Participants("11", "22") }
    }
}
