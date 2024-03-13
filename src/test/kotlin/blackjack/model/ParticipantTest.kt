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
    fun `참가자들은 게임 시작시 2장의 카드를 갖고 시작 해야한다`() {
        val cardDeck = CardDeck()
        val participant = MockParticipant(name = "꼬상")

        participant.initDraw(cardDeck)
        assertThat(participant.getCards().size).isEqualTo(Participant.INIT_HAND_CARD_COUNT)
    }

    @Test
    fun `참가자는 다른 참가자와 정상적으로 승부를 판단한다`() {
        val winPlayer = MockParticipant(name = "꼬상")
        val losePlayer = MockParticipant(name = "누누")

        val aceCard = Card(Denomination.ACE, Suit.SPADE)
        val kingCard = Card(Denomination.KING, Suit.SPADE)
        winPlayer.draw(aceCard)
        losePlayer.draw(kingCard)

        val result = winPlayer.judgeBlackState(losePlayer)
        assertThat(result).isEqualTo(Result.WIN)
    }
}
