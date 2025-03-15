package blackjack.domain.participant

import blackjack.model.participant.Dealer
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    @Test
    fun `플레이어 수가 0명 이하이면 오류가 발생한다`() {
        // given
        val dealer = Dealer.create(Name("딜러"))

        // when & then
        assertThrows<IllegalArgumentException> {
            Participants(dealer, Players.from(emptyList()))
        }
    }
}
