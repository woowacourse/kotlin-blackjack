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

    @Test
    fun `블랙잭 게임 초기상태 설정 확인 테스트, (2장씩 갖고 있어야 함)`() {
        val cardDeck = CardDeck()
        val participant = MockParticipant(name = "꼬상")

        repeat(Participant.INIT_HAND_CARD_COUNT) {
            participant.draw(cardDeck.draw())
        }
        assertThat(participant.getCards().size).isEqualTo(Participant.INIT_HAND_CARD_COUNT)
    }
}
