package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape
import blackjack.domain.enums.UserChoice
import blackjack.fixture.playersFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun `게임을 시작하면 각 플레이어와 딜러는 2장의 카드를 지급받는다`() {
        val players = playersFixture()
        val game = BlackJackGame(players, Deck())
        game.handOutInitializedCards(2)
        assertThat(players.first().cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어가 hit을 선택하면 카드를 한 장 추가한다`() {
        val players = playersFixture()
        val game = BlackJackGame(players, Deck())
        players.first().addCard(TrumpCard(CardTier.JACK, Shape.DIA))
        players.first().addCard(TrumpCard(CardTier.JACK, Shape.DIA))
        game.playGame(
            getPlayerChoice = { UserChoice.from("y") },
            onPlayerStateUpdated = {},
        )
        assertThat(players.first().cards.size).isEqualTo(3)
    }

    @Test
    fun `플레이어가 stay를 선택하면 카드의 장수가 유지된다`() {
        val players = playersFixture()
        val game = BlackJackGame(players, Deck())

        game.playGame(
            getPlayerChoice = { UserChoice.from("n") },
            onPlayerStateUpdated = {},
        )
        assertThat(players.first().cards.size).isEqualTo(0)
    }
}
