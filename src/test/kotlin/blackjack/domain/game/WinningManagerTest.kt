package blackjack.domain.game

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.game.WinningManager
import blackjack.model.game.WinningState.LOSE
import blackjack.model.game.WinningState.PUSH
import blackjack.model.game.WinningState.WIN_DEFAULT
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningManagerTest {
    private lateinit var dealer: Dealer
    private lateinit var players: Players
    private lateinit var winningManager: WinningManager

    @BeforeEach
    fun setup() {
        dealer = Dealer.create()

        val playerNames = listOf("공백")
        players = Players.from(playerNames)
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
        winningManager = WinningManager(dealer, players)
        val playerResult = winningManager.playerResults()

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
        winningManager = WinningManager(dealer, players)
        val playerResult = winningManager.playerResults()

        // then
        assertThat(playerResult.values.first()).isEqualTo(WIN_DEFAULT)
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
        winningManager = WinningManager(dealer, players)
        val playerResult = winningManager.playerResults()

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
        winningManager = WinningManager(dealer, players)
        val playerResult = winningManager.playerResults()

        // then
        assertThat(playerResult.values.first()).isEqualTo(LOSE)
    }
}
