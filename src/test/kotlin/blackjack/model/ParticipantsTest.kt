package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    class MockParticipant(name: String) : Participant(name)

    @Test
    fun `정상적인 참가자들 Hit 상태 리스트 반환 테스트 `() {
        val mockNunuParticipant = MockParticipant("누누")
        val mockKkosangParticipant = MockParticipant("꼬상")
        val participants = Participants(listOf(mockNunuParticipant, mockKkosangParticipant))

        assertThat(participants.getAliveParticipants().size).isEqualTo(2)
    }
}
