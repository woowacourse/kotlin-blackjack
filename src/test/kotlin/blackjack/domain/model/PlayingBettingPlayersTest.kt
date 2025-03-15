package blackjack.domain.model

import blackjack.domain.model.playing.PlayingDealer
import blackjack.domain.model.playing.PlayingParticipants
import blackjack.domain.model.playing.PlayingPlayer
import org.junit.jupiter.api.BeforeEach

class PlayingBettingPlayersTest {
    private lateinit var playingParticipants: PlayingParticipants
    private val playingDealer = PlayingDealer()
    private val playingPlayer1 = PlayingPlayer("동전")
    private val playingPlayer2 = PlayingPlayer("단전")

    @BeforeEach
    fun setUp() {
        playingParticipants = PlayingParticipants(playingDealer, listOf(playingPlayer1, playingPlayer2))
    }
}
