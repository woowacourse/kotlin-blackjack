package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    class MockParticipant(wallet: Wallet) : Participant(wallet) {
        override fun openInitCards(): List<Card> {
            TODO("Not yet implemented")
        }

        override fun shouldDrawCard(): Boolean {
            TODO("Not yet implemented")
        }
    }

    @Test
    fun `정상적인 Hit 상태 체크 테스트 `() {
        val name = "딜러"
        val participant = MockParticipant(wallet = Wallet(Identification(name)))
        assertThat(participant.isHitState()).isTrue()
    }
}
