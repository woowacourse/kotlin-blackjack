package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    class MockParticipant(userInformation: UserInformation) : Participant(userInformation) {
        override fun openInitCards(): List<Card> {
            TODO("Not yet implemented")
        }

        override fun checkShouldDrawCard(): Boolean {
            TODO("Not yet implemented")
        }
    }

    @Test
    fun `정상적인 Hit 상태 체크 테스트 `() {
        val name = "딜러"
        val participant = MockParticipant(userInformation = UserInformation(name))
        assertThat(participant.checkHitState()).isTrue()
    }
}
