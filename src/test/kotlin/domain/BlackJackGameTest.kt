package domain

import blackjack.domain.BlackJackGame
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    private lateinit var blackJackGame: BlackJackGame

    @BeforeEach
    fun setUp() {
        blackJackGame = BlackJackGame()

        blackJackGame.initPlayers(
            playerDataSources = listOf("woogi" to 1000, "james" to 1000, "ring" to 1000, "scott" to 1000, "sunny" to 1000)
        )
    }

    @Test
    fun `플레이어가 첫 두장을 받은 이후에 추가적인 카드를 원한다면 최소합이 21이상이 되기 전까지만 받을 수 있다`() {
        blackJackGame.drawAdditionalCardsForPlayers(
            isPlayerWantedAdditionalCards = { true }
        )

        assertThat(
            blackJackGame.players.all { player -> player.cards.getMinimumCardsScore() >= 21 }
        ).isTrue
    }
}
