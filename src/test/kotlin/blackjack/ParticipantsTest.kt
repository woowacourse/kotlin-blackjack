package blackjack

import blackjack.domain.Money
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
                Player("공백", ParticipantCards(), Money(1000)),
                Player("오이", ParticipantCards(), Money(1000)),
                Player("타마", ParticipantCards(), Money(1000)),
                Player("조이", ParticipantCards(), Money(1000)),
                Player("메다", ParticipantCards(), Money(1000)),
                Player("디렉", ParticipantCards(), Money(1000)),
                Player("제이", ParticipantCards(), Money(1000)),
                Player("비비", ParticipantCards(), Money(1000)),
            )

        assertThrows<IllegalArgumentException> { Participants(Dealer(ParticipantCards()), players) }
    }
}
