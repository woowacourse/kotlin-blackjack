package blackjack.model

import blackjack.model.amount.BetAmount
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
    fun `플레이어가 블랙잭으로 승리하면 수익은 배팅 금액의 150%이다`() {
        val result = mapOf(player to ResultType.BLACKJACK)
        val expect = 150.0

        val actual = gameManager.calculateProfit(result)[player]

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 블랙잭이 아니고 승리하면 수익은 배팅 금액의 1배이다`() {
        val result = mapOf(player to ResultType.WIN)
        val expect = 100.0

        val actual = gameManager.calculateProfit(result)[player]

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 비기면 수익은 0이다`() {
        val result = mapOf(player to ResultType.TIE)
        val expect = 0.0

        val actual = gameManager.calculateProfit(result)[player]

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 지면 수익은 배팅 금액의 -1배이다`() {
        val result = mapOf(player to ResultType.LOSS)
        val expect = -100.0

        val actual = gameManager.calculateProfit(result)[player]

        assertThat(actual).isEqualTo(expect)
    }
}
