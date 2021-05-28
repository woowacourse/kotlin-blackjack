package domain

import domain.player.Player
import org.junit.jupiter.api.BeforeEach

internal class PlayerTest {

    private lateinit var player: Player

    @BeforeEach
    fun initPlayer() {
        player = Player("ecsimsw", 100)
    }
}