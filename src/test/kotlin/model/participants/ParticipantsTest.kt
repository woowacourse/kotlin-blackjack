package model.participants

import DeckExplicitGenerationStrategy
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.result.ResultType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setUp() {
        testDeck =
            Deck.create(
                DeckExplicitGenerationStrategy(
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
        val dealer = Dealer(ParticipantState.Playing(Hand()))
        val participants = Participants.of(dealer, players)

        players.players.forEach {
            it.hit(testDeck.pop())
            it.hit(testDeck.pop())
        }
        dealer.play(testDeck)

        val expected = mapOf(ParticipantName.fromInput("pang") to ResultType.DRAW, ParticipantName.fromInput("ack") to ResultType.LOSE)

        val result = participants.getPlayersResult()
        Assertions.assertThat(result.result.values == expected.values)
    }

    @Test
    fun testGetDealerResult() {
        val players = Players.ofList(listOf("pang", "ack"))
        val dealer = Dealer(ParticipantState.Playing(Hand()))
        val participants = Participants.of(dealer, players)

        players.players.forEach {
            it.hit(testDeck.pop())
            it.hit(testDeck.pop())
        }
        dealer.play(testDeck)

        val expected = mapOf(ResultType.DRAW to 1, ResultType.WIN to 1)

        val result = participants.getDealerResult()
        Assertions.assertThat(result.result.values == expected.values)
    }
}
