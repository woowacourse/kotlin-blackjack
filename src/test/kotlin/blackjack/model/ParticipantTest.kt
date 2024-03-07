package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    class MockParticipant(name: String) : Participant(name)

    @Test
    fun `정상적인 Hit 상태 체크 테스트 `() {
        val name = "딜러"
        val participant = MockParticipant(name = name)
        assertThat(participant.checkHitState()).isTrue()
    }
}
