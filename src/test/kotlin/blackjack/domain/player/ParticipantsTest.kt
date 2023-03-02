package blackjack.domain.player

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {

    @Test
    fun `참가자들을 생성할 수 있다`() {
        assertDoesNotThrow { Participants("11", "22") }
    }

    @Test
    fun `참가자 인원은 2명 이상 8명 이하이다`() {
        assertAll(
            { assertDoesNotThrow { Participants("11", "22") } },
            { assertDoesNotThrow { Participants("11", "22", "33", "44", "55", "66", "77", "88") } }
        )
    }

    @Test
    fun `참가자 인원은 2명 미만이거나 8명 초과하면 에러가 발생한다`() {
        assertAll(
            { assertThrows<IllegalArgumentException> { Participants("11") } },
            {
                assertThrows<IllegalArgumentException> {
                    Participants("11", "22", "33", "44", "55", "66", "77", "88", "99")
                }
            }
        )
    }
}
