package model

import model.card.Deck
import model.participants.Dealer
import model.participants.ParticipantName
import model.participants.Players
import model.result.ResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var testDeck: Deck
    private lateinit var players: Players
    private lateinit var dealer: Dealer
    private lateinit var game: Game

    @BeforeEach
    fun setUp() {
        testDeck =
            createTestDeck(
                HEART_JACK,
                HEART_JACK,
                HEART_SIX,
                HEART_FOUR,
                HEART_JACK,
                HEART_JACK,
                HEART_JACK,
            )
        players = createPlayers()
        dealer = createDealer()
        game = Game.create(dealer, players, testDeck)
    }

    @Test
    fun `게임을 플레이 했을 때 플레이어의 결과를 판단할 수 있다`() {
        game.handOut()
        game.playDealer { _ -> }

        val expected = mapOf(ParticipantName.fromInput("pang") to ResultType.DRAW, ParticipantName.fromInput("ack") to ResultType.LOSE)
        val actual = game.getPlayersResult()

        assertThat(actual.result.values == expected.values)
    }

    @Test
    fun `게임을 플레이 했을 때 딜러의 결과를 판단할 수 있다`() {
        game.handOut()
        game.playDealer { _ -> }

        val expected = mapOf(ResultType.DRAW to 1, ResultType.WIN to 1)
        val actual = game.getDealerResult()

        assertThat(actual.result.values == expected.values)
    }
}
