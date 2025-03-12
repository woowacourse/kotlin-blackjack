package blackjack.domain

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.game.ResultManager
import blackjack.model.participant.Dealer
import blackjack.model.participant.Hand
import blackjack.model.participant.Players
import blackjack.model.rule.ScoreCalculator
import blackjack.model.rule.WinningResult.LOSE
import blackjack.model.rule.WinningResult.PUSH
import blackjack.model.rule.WinningResult.WIN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResultManagerTest {
    private lateinit var scoreCalculator: ScoreCalculator
    private lateinit var dealer: Dealer
    private lateinit var players: Players
    private lateinit var resultManager: ResultManager

    @BeforeEach
    fun setup() {
        scoreCalculator = ScoreCalculator()
        dealer = Dealer(hand = Hand(scoreCalculator))

        val playerNames = listOf("공백")
        players = Players.from(playerNames, scoreCalculator)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 같으면 무승부를 반환한다`() {
        // given
        dealer.addAll(
            listOf(
                Card(CardRank.NINE, CardSuit.CLUB),
                Card(CardRank.SEVEN, CardSuit.DIAMOND),
            ),
        )

        players.value.first().addAll(
            listOf(
                Card(CardRank.NINE, CardSuit.CLUB),
                Card(CardRank.SEVEN, CardSuit.DIAMOND),
            ),
        )

        // when
        resultManager = ResultManager(dealer, players)
        val playerResult = resultManager.playerResults()

        // then
        assertThat(playerResult.values.first()).isEqualTo(PUSH)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 높으면 우승을 반환한다`() {
        // given
        dealer.addAll(
            listOf(
                Card(CardRank.NINE, CardSuit.CLUB),
                Card(CardRank.SEVEN, CardSuit.DIAMOND),
            ),
        )

        players.value.first().addAll(
            listOf(
                Card(CardRank.ACE, CardSuit.SPADE),
                Card(CardRank.KING, CardSuit.HEART),
            ),
        )

        // when
        resultManager = ResultManager(dealer, players)
        val playerResult = resultManager.playerResults()

        // then
        assertThat(playerResult.values.first()).isEqualTo(WIN)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 낮으면 패배를 반환한다`() {
        // given
        dealer.addAll(
            listOf(
                Card(CardRank.ACE, CardSuit.SPADE),
                Card(CardRank.KING, CardSuit.HEART),
            ),
        )

        players.value.first().addAll(
            listOf(
                Card(CardRank.NINE, CardSuit.CLUB),
                Card(CardRank.SEVEN, CardSuit.DIAMOND),
            ),
        )

        // when
        resultManager = ResultManager(dealer, players)
        val playerResult = resultManager.playerResults()

        // then
        assertThat(playerResult.values.first()).isEqualTo(LOSE)
    }

    @Test
    fun `딜러와 플레이어가 모두 버스트된 경우 플레이어는 패배한다`() {
        // given
        dealer.addAll(
            listOf(
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.KING, CardSuit.HEART),
            ),
        )

        players.value.first().addAll(
            listOf(
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.KING, CardSuit.HEART),
            ),
        )

        // when
        resultManager = ResultManager(dealer, players)
        val playerResult = resultManager.playerResults()

        // then
        assertThat(playerResult.values.first()).isEqualTo(LOSE)
    }
}
