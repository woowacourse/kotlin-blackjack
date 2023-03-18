package blackjack.domain.player

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {

    @Test
    fun `참가자들을 생성할 수 있다`() {
        assertDoesNotThrow { Participants(Pair("11", 5000), Pair("22", 8000)) }
    }

    @Test
    fun `참가자 인원은 2명 이상 8명 이하이다`() {
        assertAll(
            { assertDoesNotThrow { Participants(Pair("11", 5000), Pair("22", 8000)) } },
            {
                assertDoesNotThrow {
                    Participants(
                        Pair("11", 5000), Pair("22", 8000),
                        Pair("33", 5000), Pair("44", 8000),
                        Pair("55", 5000), Pair("66", 8000),
                        Pair("77", 5000), Pair("88", 8000)
                    )
                }
            }
        )
    }

    @Test
    fun `참가자 인원은 2명 미만이거나 8명 초과하면 에러가 발생한다`() {
        assertAll(
            { assertThrows<IllegalArgumentException> { Participants(Pair("11", 5000)) } },
            {
                assertThrows<IllegalArgumentException> {
                    Participants(
                        Pair("11", 5000), Pair("22", 8000),
                        Pair("33", 5000), Pair("44", 8000),
                        Pair("55", 5000), Pair("66", 8000),
                        Pair("77", 5000), Pair("88", 8000),
                        Pair("99", 8000)
                    )
                }
            }
        )
    }
}
