package model

import TestDeck
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
        val players = Players.from(listOf("pang", "ack"), testDeck)
        val dealer = Dealer(Hand(testDeck))
        players.players.forEach {
            it.hit()
            it.hit()
        }

        dealer.play()

        val expected = mapOf(Name.fromInput("pang") to Result.DRAW, Name.fromInput("ack") to Result.LOSE)
        val result = Judge.getPlayersResult(players, dealer)
        assertThat(result.values == expected.values)
    }

    @Test
    fun testGetDealerResult() {
        val playersResult = mapOf(Name.fromInput("pang") to Result.DRAW, Name.fromInput("ack") to Result.LOSE)
        val dealerResult = Judge.getDealerResult(playersResult)

        val expected = mapOf(Result.DRAW to 1, Result.WIN to 1)
        assertThat(dealerResult.values == expected.values)
    }
}
