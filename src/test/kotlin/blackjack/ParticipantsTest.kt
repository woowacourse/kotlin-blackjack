package blackjack

import blackjack.domain.ParticipantCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    @Test
    fun `플레이어의 수는 최대 플레이어 수인 7명을 초과할 수 없다`() {
        val players =
            listOf(
                Player("공백", ParticipantCards()),
                Player("오이", ParticipantCards()),
                Player("타마", ParticipantCards()),
                Player("조이", ParticipantCards()),
                Player("메다", ParticipantCards()),
                Player("디렉", ParticipantCards()),
                Player("제이", ParticipantCards()),
                Player("비비", ParticipantCards()),
            )

        assertThrows<IllegalArgumentException> { Participants(Dealer(ParticipantCards()), players) }
    }
}
