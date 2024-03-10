package blackjack

import blackjack.model.CardDeck
import blackjack.model.GameState
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.ParticipantName
import blackjack.model.Participants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var participants: Participants
    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        val players =
            listOf(
                Player(ParticipantName("pobi")),
                Player(ParticipantName("woni")),
            )
        participants =
            Participants(
                dealer,
                players,
            )
        cardDeck = CardDeck()
    }

    @Test
    fun `초기 카드 2장 딜링 확인`() {
        dealer.initialCardDealing(participants, cardDeck)

        assertThat(participants.dealer.gameInformation.cards.size).isEqualTo(2)
        assertThat(participants.players.all { player -> player.gameInformation.cards.size == 2 }).isTrue()
    }

    @Test
    fun `초기 카드 2장 딜링 후 상태 HIT로 변경 확인`() {
        dealer.initialCardDealing(participants, cardDeck)

        assertThat(participants.dealer.gameInformation.state == GameState.Running.HIT).isTrue
        assertThat(participants.players.all { player -> player.gameInformation.state == GameState.Running.HIT }).isTrue
    }
}
