package blackjack.domain.model.progress

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Number
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Locale

class WinLossStatisticsTest {
    private val dealerBlackJack = Dealer(listOf(Card(Number.ACE), Card(Number.JACK)))
    private val dealer21Normal = Dealer(listOf(Card(Number.NINE), Card(Number.KING), Card(Number.TWO)))
    private val dealer19 = Dealer(listOf(Card(Number.NINE), Card(Number.KING)))
    private val dealer18 = Dealer(listOf(Card(Number.EIGHT), Card(Number.QUEEN)))
    private val dealerBust = Dealer(listOf(Card(Number.KING), Card(Number.SIX), Card(Number.QUEEN)))

    private val playerBlackJack = Player(cards = listOf(Card(Number.ACE), Card(Number.JACK)))
    private val player21Normal = Player(cards = listOf(Card(Number.NINE), Card(Number.KING), Card(Number.TWO)))
    private val player19 = Player(cards = listOf(Card(Number.NINE), Card(Number.KING)))
    private val player18 = Player(cards = listOf(Card(Number.EIGHT), Card(Number.KING)))
    private val playerBust = Player(cards = listOf(Card(Number.KING), Card(Number.SIX), Card(Number.QUEEN)))

    private lateinit var winLossStatistics: WinLossStatistics

    @BeforeEach
    fun init() {
        winLossStatistics = WinLossStatistics()
    }

    @Test
    fun `딜러와 점수가 같을 경우 플레이어의 무승부임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealer19, player19)).isEqualTo(
            WinLoss.DRAW,
        )
    }

    @Test
    fun `딜러보다 점수가 클 경우 플레이어의 승리임을 받아올 수 있다`() {
        assertThat(
            winLossStatistics.calculatePlayerWinLoss(
                dealer18,
                player19,
            ),
        ).isEqualTo(
            WinLoss.WIN,
        )
    }

    @Test
    fun `딜러보다 점수가 작을 경우 플레이어의 패배임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealer19, player18)).isEqualTo(WinLoss.LOSE)
    }

    @Test
    fun `딜러가 버스트고 플레이어가 버스트가 아닐 경우 플레이어의 승리임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealerBust, player18)).isEqualTo(WinLoss.WIN)
    }

    @Test
    fun `딜러가 버스트고 플레이어가 버스트일 경우 플레이어의 패배임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealerBust, playerBust)).isEqualTo(
            WinLoss.LOSE,
        )
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어가 일반 21일 경우 플레이어의 패배임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealerBlackJack, player21Normal)).isEqualTo(
            WinLoss.LOSE,
        )
    }

    @Test
    fun `딜러가 일반 21이고 플레이어가 일반 21일 경우 무승부임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealer21Normal, player21Normal)).isEqualTo(
            WinLoss.DRAW,
        )
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어가 블랙잭일 경우 무승부임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealerBlackJack, playerBlackJack)).isEqualTo(
            WinLoss.DRAW,
        )
    }

    @Test
    fun `딜러가 일반 21이고 플레이어가 블랙잭일 경우 플레이어의 승리임을 받아올 수 있다`() {
        assertThat(winLossStatistics.calculatePlayerWinLoss(dealer21Normal, playerBlackJack)).isEqualTo(
            WinLoss.WIN,
        )
    }

    @Test
    fun `딜러의 전체 승 무 패 결과를 텍스트로 받아올 수 있다`() {
        winLossStatistics.calculatePlayerWinLoss(dealerBlackJack, playerBlackJack) // 무승부
        winLossStatistics.calculatePlayerWinLoss(dealer18, player19) // 딜러 패배

        val actualDealerWinLossText = OutputView(Locale.KOREAN).makeDealerWinLossText(winLossStatistics)
        val expectedDealerWinLossText = "1무 1패"

        assertThat(actualDealerWinLossText).isEqualTo(expectedDealerWinLossText)
    }
}
