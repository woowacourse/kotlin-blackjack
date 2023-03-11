package blackjack.controller

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.ParticipantCards
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
        assertAll(
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
                            "반달", listOf(Card(CardNumber.EIGHT, Suit.CLOVER), Card(CardNumber.JACK, Suit.CLOVER))
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
            }
        )
    }

    @Test
    fun `딜러가 몇 번 카드를 더 받는지 확인`() {
        assertThat(outputView.dealerHitCount).isEqualTo(1)
    }

    @Test
    fun `참여자들의 최종 카드 확인`() {
        assertAll(
            {
                assertThat(outputView.dealerResult.name to outputView.dealerResult.cards)
                    .isEqualTo(
                        "딜러" to
                            listOf(
                                Card(CardNumber.JACK, Suit.SPADE), Card(CardNumber.FIVE, Suit.SPADE),
                                Card(CardNumber.FOUR, Suit.SPADE)
                            )
                    )
            },
            {
                assertThat(outputView.playerResults[0].name to outputView.playerResults[0].cards)
                    .isEqualTo(
                        "부나" to listOf(Card(CardNumber.QUEEN, Suit.DIAMOND), Card(CardNumber.KING, Suit.DIAMOND))
                    )
            },
            {
                assertThat(outputView.playerResults[1].name to outputView.playerResults[1].cards)
                    .isEqualTo(
                        "글로" to listOf(Card(CardNumber.KING, Suit.HEART), Card(CardNumber.ACE, Suit.HEART))
                    )
            },
            {
                assertThat(outputView.playerResults[2].name to outputView.playerResults[2].cards)
                    .isEqualTo(
                        "반달" to
                            listOf(
                                Card(CardNumber.EIGHT, Suit.CLOVER), Card(CardNumber.JACK, Suit.CLOVER),
                                Card(CardNumber.THREE, Suit.CLOVER), Card(CardNumber.KING, Suit.CLOVER)
                            )
                    )
            },
            {
                assertThat(outputView.playerResults[3].name to outputView.playerResults[3].cards)
                    .isEqualTo(
                        "제이슨" to listOf(Card(CardNumber.JACK, Suit.HEART), Card(CardNumber.NINE, Suit.HEART))
                    )
            }
        )
    }

    @Test
    fun `참여자들의 최종 점수를 확인`() {
        assertAll(
            {
                assertThat(outputView.dealerResult.name to outputView.dealerResult.score)
                    .isEqualTo("딜러" to 19)
            },
            {
                assertThat(outputView.playerResults[0].name to outputView.playerResults[0].score)
                    .isEqualTo("부나" to 20)
            },
            {
                assertThat(outputView.playerResults[1].name to outputView.playerResults[1].score)
                    .isEqualTo("글로" to 21)
            },
            {
                assertThat(outputView.playerResults[2].name to outputView.playerResults[2].score)
                    .isEqualTo("반달" to 31)
            },
            {
                assertThat(outputView.playerResults[3].name to outputView.playerResults[3].score)
                    .isEqualTo("제이슨" to 19)
            }
        )
    }

    @Test
    fun `참여자들의 최종 승패를 확인`() {
        assertAll(
            { assertThat(outputView.dealerResult.name).isEqualTo("딜러") },
            { assertThat(outputView.dealerResult.win).isEqualTo(1) },
            { assertThat(outputView.dealerResult.draw).isEqualTo(1) },
            { assertThat(outputView.dealerResult.lose).isEqualTo(2) },
            {
                assertThat(outputView.playerResults[0].name to outputView.playerResults[0].gameResult)
                    .isEqualTo("부나" to GameResult.WIN)
            },
            {
                assertThat(outputView.playerResults[1].name to outputView.playerResults[1].gameResult)
                    .isEqualTo("글로" to GameResult.BLACKJACK)
            },
            {
                assertThat(outputView.playerResults[2].name to outputView.playerResults[2].gameResult)
                    .isEqualTo("반달" to GameResult.LOSE)
            },
            {
                assertThat(outputView.playerResults[3].name to outputView.playerResults[3].gameResult)
                    .isEqualTo("제이슨" to GameResult.DRAW)
            }
        )
    }

    @Test
    fun `참여자들의 최종 수익을 확인`() {
        assertAll(
            { assertThat(outputView.dealerResult.name to outputView.dealerResult.profit).isEqualTo("딜러" to -11000) },
            {
                assertThat(outputView.playerResults[0].name to outputView.playerResults[0].profit)
                    .isEqualTo("부나" to 1000)
            },
            {
                assertThat(outputView.playerResults[1].name to outputView.playerResults[1].profit)
                    .isEqualTo("글로" to 15000)
            },
            {
                assertThat(outputView.playerResults[2].name to outputView.playerResults[2].profit)
                    .isEqualTo("반달" to -5000)
            },
            {
                assertThat(outputView.playerResults[3].name to outputView.playerResults[3].profit)
                    .isEqualTo("제이슨" to 0)
            }
        )
    }
}
