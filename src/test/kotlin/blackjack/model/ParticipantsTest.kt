package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    class MockParticipant(name: String) : Participant(name)
    class Participants(private val participants: List<Participant>) {
        init {
            require(participants.size <= MAX_PARTICIPANTS_SIZE) { ERROR_MAX_PARTICIPANTS_SIZE }
        }

        fun getAliveParticipants(): List<Participant> {
            return participants.filter {
                it.checkHitState()
            }
        }

        fun getParticipants(): List<Participant> {
            return participants
        }

        companion object {
            private const val MAX_PARTICIPANTS_SIZE: Int = 8
            private const val ERROR_MAX_PARTICIPANTS_SIZE = "게임 참가자의 수는 ${MAX_PARTICIPANTS_SIZE}을 초과할 수 없습니다."
        }
    }

    @Test
    fun `정상적인 참가자들 Hit 상태 리스트 반환 테스트 `() {
        val mockNunuParticipant = MockParticipant("누누")
        val mockKkosangParticipant = MockParticipant("꼬상")
        val participants = Participants(listOf(mockNunuParticipant, mockKkosangParticipant))

        assertThat(participants.getAliveParticipants().size).isEqualTo(2)
    }
}
