package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {
    private lateinit var playerGroup: PlayerGroup
    private lateinit var dealer: Dealer
    private lateinit var participants: Participants

    @BeforeEach
    fun setting() {
        playerGroup = PlayerGroup()
        playerGroup.addPlayer(listOf("호두", "에디", "레오", "예니"))
        dealer = Dealer()
        participants = Participants(dealer, playerGroup)
    }

    @Test
    fun `게임이 시작되면 플레이어와 딜러에게 카드를 1장씩 나눠준다`() {
        participants.initParticipantsDeck()

        Assertions.assertThat(dealer.hand.cards.size).isEqualTo(1)
        participants.playerGroup.players.forEach { player ->
            Assertions.assertThat(player.hand.cards.size).isEqualTo(1)
        }
    }
}
