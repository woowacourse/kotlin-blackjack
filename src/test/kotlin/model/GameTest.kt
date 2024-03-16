package model

import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.participants.Dealer
import model.participants.Players
import org.junit.jupiter.api.BeforeEach

class GameTest {
    private lateinit var testDeck: Deck
    private lateinit var players: Players
    private lateinit var dealer: Dealer
    private lateinit var game: Game

    @BeforeEach
    fun setUp() {
        testDeck =
            makeTestDeck(
                Card(ValueType.JACK, MarkType.SPADE),
                Card(ValueType.JACK, MarkType.SPADE),
                Card(ValueType.SIX, MarkType.SPADE),
                Card(ValueType.FOUR, MarkType.SPADE),
                Card(ValueType.JACK, MarkType.SPADE),
                Card(ValueType.JACK, MarkType.SPADE),
                Card(ValueType.JACK, MarkType.SPADE),
            )
        players = makePlayers()
        dealer = makeDealer()
        game = Game.create(dealer, players, testDeck)
    }

//    @Test
//    fun `게임을 플레이 했을 때 결과를 판단할 수 있다`() {
//        game.getPlayers().players.forEach {
//            it.hit(testDeck.pop())
//            it.hit(testDeck.pop())
//        }
//        game.getDealer().play(testDeck)
//
//        val expected = mapOf(ParticipantName.fromInput("pang") to ResultType.DRAW, ParticipantName.fromInput("ack") to ResultType.LOSE)
//        val result = game.getPlayersResult()
//
//        Assertions.assertThat(result.result.values == expected.values)
//    }

//    @Test
//    fun testGetDealerResult() {
//        players.players.forEach {
//            it.hit(testDeck.pop())
//            it.hit(testDeck.pop())
//        }
//        dealer.play(testDeck)
//
//        val expected = mapOf(ResultType.DRAW to 1, ResultType.WIN to 1)
//
//        val result = game.getDealerResult()
//        Assertions.assertThat(result.result.values == expected.values)
//    }
}
