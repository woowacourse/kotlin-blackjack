package blackjackgame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    internal fun createPlayer() {
        val rocki = Player("rocki")

        assertThat(rocki.name).isEqualTo("rocki")
    }
}