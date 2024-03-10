package blackjack

import blackjack.model.CardDeck
import blackjack.model.GameState
import blackjack.model.Participant.Player
import blackjack.model.ParticipantName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun setUp() {
        player = Player(ParticipantName("채드"))
        cardDeck = CardDeck()
    }

    @Test
    fun `카드 한장 뽑기`() {
        player.draw(cardDeck.pickCard())

        assertThat(player.gameInformation.cards.size).isEqualTo(1)
    }

    @Test
    fun `상태 HIT로 전환`() {
        player.changeStateToHit()

        assertThat(player.gameInformation.state).isEqualTo(GameState.Running.HIT)
    }
}
