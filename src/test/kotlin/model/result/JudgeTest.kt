package model.result

import TestDeck
import model.card.Card
import model.participants.Dealer
import model.participants.Hand
import model.participants.ParticipantName
import model.participants.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JudgeTest {
    private lateinit var testDeck: TestDeck

    @BeforeEach
    fun setUp() {
        testDeck =
            TestDeck(
                mutableListOf(
                    Card.from(11),
                    Card.from(11),
                    Card.from(5),
                    Card.from(3),
                    Card.from(11),
                    Card.from(11),
                    Card.from(11),
                    Card.from(22),
                    Card.from(25),
                ),
            )
    }

    @Test
    fun `게임을 플레이 했을 때 결과를 판단할 수 있다`() {
        val players = Players.ofList(listOf("pang", "ack"), testDeck)
        val dealer = Dealer(Hand(testDeck))
        players.players.forEach {
            it.hit()
            it.hit()
        }

        dealer.play()

        val expected = mapOf(ParticipantName.fromInput("pang") to ResultType.DRAW, ParticipantName.fromInput("ack") to ResultType.LOSE)
        val result = Judge.getPlayersResult(players, dealer)
        assertThat(result.result.values == expected.values)
    }

    @Test
    fun testGetDealerResult() {
        val playersResult =
            PlayersResult(mapOf(ParticipantName.fromInput("pang") to ResultType.DRAW, ParticipantName.fromInput("ack") to ResultType.LOSE))
        val dealerResult = Judge.getDealerResult(playersResult)

        val expected = mapOf(ResultType.DRAW to 1, ResultType.WIN to 1)
        assertThat(dealerResult.result.values == expected.values)
    }
}
