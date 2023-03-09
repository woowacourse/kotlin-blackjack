package blackjack.controller

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantProfit
import blackjack.domain.data.ParticipantScore
import blackjack.domain.data.PlayerResult
import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackControllerTest {
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
        "글로" to false,
        "반달" to true,
        "제이슨" to false
    )
    private val deck = listOf(
        Card(CardNumber.JACK, Suit.SPADE), // dealer1
        Card(CardNumber.QUEEN, Suit.DIAMOND), // 부나1
        Card(CardNumber.KING, Suit.HEART), // 글로1
        Card(CardNumber.ACE, Suit.CLOVER), // 반달1
        Card(CardNumber.JACK, Suit.HEART), // 제이슨1
        Card(CardNumber.FIVE, Suit.SPADE), // dealer2
        Card(CardNumber.KING, Suit.DIAMOND), // 부나2
        Card(CardNumber.ACE, Suit.HEART), // 글로2
        Card(CardNumber.JACK, Suit.CLOVER), // 반달2
        Card(CardNumber.NINE, Suit.HEART), // 제이슨2
        Card(CardNumber.QUEEN, Suit.CLOVER), // 반달3
        Card(CardNumber.KING, Suit.CLOVER), // 반달4
        Card(CardNumber.FOUR, Suit.SPADE), // dealer3
    )

    @BeforeEach
    fun setUp() {
        inputView = FakeInputView(names, money, commands)
        outputView = FakeOutputView()
        controller = BlackJackController(inputView, outputView)
    }

    @Test
    fun `블랙잭 게임 테스트`() {
        controller.start(CardDeck(deck))

        assertAll(
            // 처음 나누어준 카드
            {
                assertThat(outputView.firstOpenCards[0])
                    .isEqualTo(ParticipantCards("딜러", listOf(Card(CardNumber.JACK, Suit.SPADE))))
            },
            {
                assertThat(outputView.firstOpenCards[1])
                    .isEqualTo(
                        ParticipantCards(
                            "부나", listOf(Card(CardNumber.QUEEN, Suit.DIAMOND), Card(CardNumber.KING, Suit.DIAMOND))
                        )
                    )
            },
            {
                assertThat(outputView.firstOpenCards[2])
                    .isEqualTo(
                        ParticipantCards(
                            "글로", listOf(Card(CardNumber.KING, Suit.HEART), Card(CardNumber.ACE, Suit.HEART))
                        )
                    )
            },
            {
                assertThat(outputView.firstOpenCards[3])
                    .isEqualTo(
                        ParticipantCards(
                            "반달", listOf(Card(CardNumber.ACE, Suit.CLOVER), Card(CardNumber.JACK, Suit.CLOVER))
                        )
                    )
            },
            {
                assertThat(outputView.firstOpenCards[4])
                    .isEqualTo(
                        ParticipantCards(
                            "제이슨", listOf(Card(CardNumber.JACK, Suit.HEART), Card(CardNumber.NINE, Suit.HEART))
                        )
                    )
            },
            // 딜러가 몇 번 카드를 더 받는지 확인
            { assertThat(outputView.dealerHitCount).isEqualTo(1) },
            // 참여자들의 최종 카드 확인
            {
                assertThat(outputView.participantCards[0])
                    .isEqualTo(
                        ParticipantCards(
                            "딜러",
                            listOf(
                                Card(CardNumber.JACK, Suit.SPADE), Card(CardNumber.FIVE, Suit.SPADE),
                                Card(CardNumber.FOUR, Suit.SPADE)
                            )
                        )
                    )
            },
            {
                assertThat(outputView.participantCards[1])
                    .isEqualTo(
                        ParticipantCards(
                            "부나", listOf(Card(CardNumber.QUEEN, Suit.DIAMOND), Card(CardNumber.KING, Suit.DIAMOND))
                        )
                    )
            },
            {
                assertThat(outputView.participantCards[2])
                    .isEqualTo(
                        ParticipantCards(
                            "글로", listOf(Card(CardNumber.KING, Suit.HEART), Card(CardNumber.ACE, Suit.HEART))
                        )
                    )
            },
            {
                assertThat(outputView.participantCards[3])
                    .isEqualTo(
                        ParticipantCards(
                            "반달",
                            listOf(
                                Card(CardNumber.ACE, Suit.CLOVER), Card(CardNumber.JACK, Suit.CLOVER),
                                Card(CardNumber.QUEEN, Suit.CLOVER), Card(CardNumber.KING, Suit.CLOVER)
                            )
                        )
                    )
            },
            {
                assertThat(outputView.participantCards[4])
                    .isEqualTo(
                        ParticipantCards(
                            "제이슨", listOf(Card(CardNumber.JACK, Suit.HEART), Card(CardNumber.NINE, Suit.HEART))
                        )
                    )
            },
            // 참여자들의 최종 점수를 확인
            { assertThat(outputView.totalScores[0]).isEqualTo(ParticipantScore("딜러", 19)) },
            { assertThat(outputView.totalScores[1]).isEqualTo(ParticipantScore("부나", 20)) },
            { assertThat(outputView.totalScores[2]).isEqualTo(ParticipantScore("글로", 21)) },
            { assertThat(outputView.totalScores[3]).isEqualTo(ParticipantScore("반달", 31)) },
            { assertThat(outputView.totalScores[4]).isEqualTo(ParticipantScore("제이슨", 19)) },
            // 참여자들의 최종 승패를 확인
            { assertThat(outputView.results.dealerResult).isEqualTo(DealerResult("딜러", 1, 1, 2)) },
            { assertThat(outputView.results.playerResults[0]).isEqualTo(PlayerResult("부나", GameResult.WIN)) },
            { assertThat(outputView.results.playerResults[1]).isEqualTo(PlayerResult("글로", GameResult.BLACKJACK)) },
            { assertThat(outputView.results.playerResults[2]).isEqualTo(PlayerResult("반달", GameResult.LOSE)) },
            { assertThat(outputView.results.playerResults[3]).isEqualTo(PlayerResult("제이슨", GameResult.DRAW)) },
            // 참여자들의 최종 수익을 확인
            { assertThat(outputView.results.profits[0]).isEqualTo(ParticipantProfit("딜러", -11000)) },
            { assertThat(outputView.results.profits[1]).isEqualTo(ParticipantProfit("부나", 1000)) },
            { assertThat(outputView.results.profits[2]).isEqualTo(ParticipantProfit("글로", 15000)) },
            { assertThat(outputView.results.profits[3]).isEqualTo(ParticipantProfit("반달", -5000)) },
            { assertThat(outputView.results.profits[4]).isEqualTo(ParticipantProfit("제이슨", 0)) }
        )
    }
}
