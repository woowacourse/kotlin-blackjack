package model.result

import DeckExplicitGeneration
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.participants.Dealer
import model.participants.Hand
import model.participants.ParticipantName
import model.participants.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JudgeTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setUp() {
        testDeck =
            Deck.create(
                DeckExplicitGeneration(
                    mutableListOf(
                        Card(ValueType.JACK, MarkType.SPADE),
                        Card(ValueType.JACK, MarkType.SPADE),
                        Card(ValueType.SIX, MarkType.SPADE),
                        Card(ValueType.FOUR, MarkType.SPADE),
                        Card(ValueType.JACK, MarkType.SPADE),
                        Card(ValueType.JACK, MarkType.SPADE),
                        Card(ValueType.JACK, MarkType.SPADE),
                    ),
                ),
            )
    }

    @Test
    fun `게임을 플레이 했을 때 결과를 판단할 수 있다`() {
        val players = Players.ofList(listOf("pang", "ack"))
        val dealer = Dealer(Hand())
        players.players.forEach {
            it.hit(testDeck.pop())
            it.hit(testDeck.pop())
        }

        dealer.play(testDeck)

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
