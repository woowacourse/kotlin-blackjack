package blackjack.model

import blackjack.model.amount.BetAmount
import blackjack.model.amount.WinningMoney
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameManagerTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer
    private lateinit var gameManager: GameManager

    @BeforeEach
    fun setUp() {
        player = Player("미플", BetAmount(100))
        dealer = Dealer()
        gameManager = GameManager(dealer, listOf(player))
    }

    @Test
    fun `초기 세팅은 플레이어와 딜러에게 카드 2장씩 나눠준다`() {
        gameManager.dealInitialCardWithCount()

        assertThat(player.cards.size).isEqualTo(2)
        assertThat(dealer.cards.size).isEqualTo(2)
    }

    @Test
    fun `참가자는 카드를 한 장 드로우할 수 있다`() {
        gameManager.drawCard(player)
        gameManager.drawCard(dealer)

        assertThat(player.cards.size).isEqualTo(1)
        assertThat(dealer.cards.size).isEqualTo(1)
    }


    @Test
    fun `플레이어가 블랙잭으로 승리하면 수익은 배팅 금액의 150%이다`() {
        val resultType = ResultType.BLACKJACK
        val expect = 150.0

        val actual = gameManager.calculateProfit(resultType, player).amount

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 블랙잭이 아니고 승리하면 수익은 배팅 금액의 1배이다`() {
        val resultType = ResultType.WIN
        val expect = 100.0

        val actual = gameManager.calculateProfit(resultType, player).amount

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 비기면 수익은 0이다`() {
        val resultType = ResultType.TIE
        val expect = 0.0

        val actual = gameManager.calculateProfit(resultType, player).amount

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 지면 수익은 배팅 금액의 -1배이다`() {
        val resultType = ResultType.LOSS
        val expect = -100.0

        val actual = gameManager.calculateProfit(resultType, player).amount

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `딜러의 수익은 플레이어 수익 총 합의 -1을 곱한 값이다`() {
        val totalPlayerProfit = listOf(
            Profit(player, WinningMoney(100.0)),
            Profit(player, WinningMoney(200.0))
        )
        val expect = -300.0

        val actual = gameManager.calculateDealerProfit(totalPlayerProfit).winningMoney.amount

        assertThat(actual).isEqualTo(expect)
    }
}
