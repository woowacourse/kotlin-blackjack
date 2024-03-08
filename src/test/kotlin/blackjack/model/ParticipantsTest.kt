package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {
    private lateinit var playerGroup: PlayerGroup
    private lateinit var dealer: Dealer
    private lateinit var participants: Participants
    private lateinit var gameDeck: GameDeck

    @BeforeEach
    fun setting() {
        playerGroup = PlayerGroup()
        playerGroup.addPlayer(listOf("호두", "에디", "레오", "예니"))
        dealer = Dealer()
        participants = Participants(dealer, playerGroup)
        gameDeck = GameDeck()
    }

    @Test
    fun `게임이 시작되면 플레이어와 딜러에게 카드를 2장씩 나눠준다`() {
        participants.initSetting(gameDeck)

        assertThat(dealer.hand.cards.size).isEqualTo(Participants.INITIAL_CARD_COUNTS)
        participants.playerGroup.players.forEach { player ->
            assertThat(player.hand.cards.size).isEqualTo(Participants.INITIAL_CARD_COUNTS)
        }
    }
}
