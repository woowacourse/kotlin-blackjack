package blackjack.domain

import blackjack.domain.card.*
import blackjack.domain.participant.Participants
import blackjack.fixture.participantsFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    private lateinit var game: BlackJackGame
    private lateinit var participants: Participants

    @BeforeEach
    fun setUp() {
        participants = participantsFixture()
        val deck = Deck(CardFactoryImpl())

        game = BlackJackGame(participants, deck)
    }

    @Test
    fun `게임을 시작하면 각 플레이어와 딜러는 2장의 카드를 지급받는다`() {
        game.handOutInitializedCards(2)
        assertThat(participants.players.first().cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어가 hit을 선택하면 카드를 한 장 추가한다`() {
        val player = participants.players.first()

        player.addCard(TrumpCard(Tier.JACK, Shape.DIA))
        player.addCard(TrumpCard(Tier.JACK, Shape.DIA))

        game.playGame(
            getPlayerChoice = { UserChoice.from("y") },
            onPlayerStateUpdated = {},
        )

        assertThat(player.cards.size).isEqualTo(3)
    }

    @Test
    fun `플레이어가 stay를 선택하면 카드의 장수가 유지된다`() {
        game.playGame(
            getPlayerChoice = { UserChoice.from("n") },
            onPlayerStateUpdated = {},
        )
        assertThat(participants.players.first().cards.size).isEqualTo(0)
    }
}
