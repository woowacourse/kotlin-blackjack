package blackjack

import blackjack.model.CardDeck
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.ParticipantName
import blackjack.model.Participants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `초기 카드 세팅`() {
        val dealer = Dealer()
        val players =
            listOf(
                Player(ParticipantName("pobi")),
                Player(ParticipantName("woni")),
            )
        val participants =
            Participants(
                dealer,
                players,
            )
        val cardDeck = CardDeck()

        dealer.initialDealing(participants, cardDeck)

        assertThat(participants.dealer.gameInformation.cards.size).isEqualTo(2)
        assertThat(participants.players.all { it.gameInformation.cards.size == 2 }).isTrue()
    }
}
