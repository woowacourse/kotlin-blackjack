package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {
    private lateinit var participants: Participants
    private val dealer = Dealer()
    private val player1 = Player("A")
    private val player2 = Player("B")

    @BeforeEach
    fun setUp() {
        participants = Participants(listOf(dealer, player1, player2))
    }

    @Test
    fun `참가자들 중 딜러를 반환한다`() {
        val dealer = participants.findDealer()
        assertThat(dealer).isEqualTo(this.dealer)
    }

    @Test
    fun `참가자들 중 플레이어들을 반환한다`() {
        val players = participants.filterPlayers()
        assertThat(players).containsExactlyInAnyOrder(this.player1, this.player2)
    }
}
