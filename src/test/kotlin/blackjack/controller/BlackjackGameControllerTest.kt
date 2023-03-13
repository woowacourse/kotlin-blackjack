package blackjack.controller

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.ParticipantCards
import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackGameControllerTest {
    private lateinit var inputView: FakeInputView
    private lateinit var outputView: FakeOutputView
    private lateinit var controller: BlackJackController
    private val names = listOf("부나", "글로", "반달", "제이슨")
    private val money = mapOf(
        "부나" to 1000, // 1000
        "글로" to 10000, // 15000
        "반달" to 5000, // -5000
        "제이슨" to 2000 // 0
    )
    private val commands = mapOf(
        "부나" to false,
        "글로" to true,
        "반달" to true,
        "제이슨" to false
    )
    private val deck = listOf(
        Card(CardNumber.JACK, Suit.SPADE), // dealer1
        Card(CardNumber.QUEEN, Suit.DIAMOND), // 부나1
        Card(CardNumber.KING, Suit.HEART), // 글로1
        Card(CardNumber.EIGHT, Suit.CLOVER), // 반달1
        Card(CardNumber.JACK, Suit.HEART), // 제이슨1
        Card(CardNumber.FIVE, Suit.SPADE), // dealer2
        Card(CardNumber.KING, Suit.DIAMOND), // 부나2
        Card(CardNumber.ACE, Suit.HEART), // 글로2
        Card(CardNumber.JACK, Suit.CLOVER), // 반달2
        Card(CardNumber.NINE, Suit.HEART), // 제이슨2
        Card(CardNumber.THREE, Suit.CLOVER), // 반달3
        Card(CardNumber.KING, Suit.CLOVER), // 반달4
        Card(CardNumber.FOUR, Suit.SPADE), // dealer3
    )

    @BeforeEach
    fun setUp() {
        inputView = FakeInputView(names, money, commands)
        outputView = FakeOutputView()
        controller = BlackJackController(inputView, outputView)

        controller.start(CardDeck(deck))
    }

    @Test
    fun `참여자들이 처음 공개하는 카드 확인`() {
        val soft = SoftAssertions()

        val expected0 = ParticipantCards("딜러", listOf(Card(CardNumber.JACK, Suit.SPADE)))
        soft.assertThat(outputView.firstOpenCards[0]).isEqualTo(expected0)

        val expected1 =
            ParticipantCards("부나", listOf(Card(CardNumber.QUEEN, Suit.DIAMOND), Card(CardNumber.KING, Suit.DIAMOND)))
        soft.assertThat(outputView.firstOpenCards[1]).isEqualTo(expected1)

        val expected2 =
            ParticipantCards("글로", listOf(Card(CardNumber.KING, Suit.HEART), Card(CardNumber.ACE, Suit.HEART)))
        soft.assertThat(outputView.firstOpenCards[2]).isEqualTo(expected2)

        val expected3 =
            ParticipantCards("반달", listOf(Card(CardNumber.EIGHT, Suit.CLOVER), Card(CardNumber.JACK, Suit.CLOVER)))
        soft.assertThat(outputView.firstOpenCards[3]).isEqualTo(expected3)

        val expected4 =
            ParticipantCards("제이슨", listOf(Card(CardNumber.JACK, Suit.HEART), Card(CardNumber.NINE, Suit.HEART)))
        soft.assertThat(outputView.firstOpenCards[4]).isEqualTo(expected4)
    }

    @Test
    fun `딜러가 몇 번 카드를 더 받는지 확인`() {
        assertThat(outputView.dealerHitCount).isEqualTo(1)
    }

    @Test
    fun `참여자들의 최종 카드 확인`() {
        val soft = SoftAssertions()

        val expectedDealer = "딜러" to listOf(
            Card(CardNumber.JACK, Suit.SPADE), Card(CardNumber.FIVE, Suit.SPADE),
            Card(CardNumber.FOUR, Suit.SPADE)
        )
        soft.assertThat(outputView.dealerResult.name to outputView.dealerResult.cards).isEqualTo(expectedDealer)

        val expectedPlayer0 = "부나" to listOf(Card(CardNumber.QUEEN, Suit.DIAMOND), Card(CardNumber.KING, Suit.DIAMOND))
        soft.assertThat(outputView.playerResults[0].name to outputView.playerResults[0].cards)
            .isEqualTo(expectedPlayer0)

        val expectedPlayer1 = "글로" to listOf(Card(CardNumber.KING, Suit.HEART), Card(CardNumber.ACE, Suit.HEART))
        soft.assertThat(outputView.playerResults[1].name to outputView.playerResults[1].cards)
            .isEqualTo(expectedPlayer1)

        val expectedPlayer2 = "반달" to listOf(
            Card(CardNumber.EIGHT, Suit.CLOVER), Card(CardNumber.JACK, Suit.CLOVER),
            Card(CardNumber.THREE, Suit.CLOVER), Card(CardNumber.KING, Suit.CLOVER)
        )
        soft.assertThat(outputView.playerResults[2].name to outputView.playerResults[2].cards)
            .isEqualTo(expectedPlayer2)

        val expectedPlayer3 = "제이슨" to listOf(Card(CardNumber.JACK, Suit.HEART), Card(CardNumber.NINE, Suit.HEART))
        soft.assertThat(outputView.playerResults[3].name to outputView.playerResults[3].cards)
            .isEqualTo(expectedPlayer3)
    }

    @Test
    fun `참여자들의 최종 점수를 확인`() {
        val soft = SoftAssertions()

        val actualDealer = outputView.dealerResult.name to outputView.dealerResult.score
        soft.assertThat(actualDealer).isEqualTo("딜러" to 19)

        val actualPlayer0 = outputView.playerResults[0].name to outputView.playerResults[0].score
        soft.assertThat(actualPlayer0).isEqualTo("부나" to 20)

        val actualPlayer1 = outputView.playerResults[1].name to outputView.playerResults[1].score
        soft.assertThat(actualPlayer1).isEqualTo("글로" to 21)

        val actualPlayer2 = outputView.playerResults[2].name to outputView.playerResults[2].score
        soft.assertThat(actualPlayer2).isEqualTo("반달" to 31)

        val actualPlayer3 = outputView.playerResults[3].name to outputView.playerResults[3].score
        soft.assertThat(actualPlayer3).isEqualTo("제이슨" to 19)
    }

    @Test
    fun `참여자들의 최종 승패를 확인`() {
        val soft = SoftAssertions()

        val actualDealerName = outputView.dealerResult.name
        soft.assertThat(actualDealerName).isEqualTo("딜러")
        val actualDealerWin = outputView.dealerResult.win
        soft.assertThat(actualDealerWin).isEqualTo(1)
        val actualDealerDraw = outputView.dealerResult.draw
        soft.assertThat(actualDealerDraw).isEqualTo(1)
        val actualDealerLose = outputView.dealerResult.lose
        soft.assertThat(actualDealerLose).isEqualTo(2)

        val actualPlayer0 = outputView.playerResults[0].name to outputView.playerResults[0].gameResult
        soft.assertThat(actualPlayer0).isEqualTo("부나" to GameResult.WIN)

        val actualPlayer1 = outputView.playerResults[1].name to outputView.playerResults[1].gameResult
        soft.assertThat(actualPlayer1).isEqualTo("글로" to GameResult.BLACKJACK)

        val actualPlayer2 = outputView.playerResults[2].name to outputView.playerResults[2].gameResult
        soft.assertThat(actualPlayer2).isEqualTo("반달" to GameResult.LOSE)

        val actualPlayer3 = outputView.playerResults[3].name to outputView.playerResults[3].gameResult
        soft.assertThat(actualPlayer3).isEqualTo("제이슨" to GameResult.DRAW)
    }

    @Test
    fun `참여자들의 최종 수익을 확인`() {
        val soft = SoftAssertions()

        val actualDealer = outputView.dealerResult.name to outputView.dealerResult.profit
        soft.assertThat(actualDealer).isEqualTo("딜러" to -11000)

        val actualPlayer0 = outputView.playerResults[0].name to outputView.playerResults[0].profit
        soft.assertThat(actualPlayer0).isEqualTo("부나" to 1000)

        val actualPlayer1 = outputView.playerResults[1].name to outputView.playerResults[1].profit
        soft.assertThat(actualPlayer1).isEqualTo("글로" to 15000)

        val actualPlayer2 = outputView.playerResults[2].name to outputView.playerResults[2].profit
        soft.assertThat(actualPlayer2).isEqualTo("반달" to -5000)

        val actualPlayer3 = outputView.playerResults[3].name to outputView.playerResults[3].profit
        soft.assertThat(actualPlayer3).isEqualTo("제이슨" to 0)
    }
}
