package blackjack

import blackjack.CardFixture.Companion.CLOVER_NINE
import blackjack.model.user.Participant
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
        participant.addCard(CLOVER_NINE)
        assertThat(participant.cards.size).isEqualTo(1)
    }
}
