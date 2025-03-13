package blackjack

import blackjack.model.GameStatus
import blackjack.model.Participant
import blackjack.model.card.Card
import blackjack.model.card.Number
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantTest {
    private lateinit var participant: Participant

    @BeforeEach
    fun setUp() {
        participant = Participant("참가자이름")
    }

    @Test
    fun `참여자는 카드 한 장을 받을 수 있다`() {
        participant.addCard(Card(Shape.SPADE, Number.NINE))
        assertThat(participant.cards.size).isEqualTo(1)
    }

    @Test
    fun `참여자 게임 진행 상태는 변수를 호출하는 시점에 평가된다`() {
        participant.addCard(Card(Shape.SPADE, Number.TEN))
        assertThat(participant.gameStatus).isEqualTo(GameStatus.IN_PROGRESS)
        participant.addCard(Card(Shape.SPADE, Number.ACE))
        assertThat(participant.gameStatus).isEqualTo(GameStatus.BLACKJACK)
    }
}
