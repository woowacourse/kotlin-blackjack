package blackjack.model

import blackjack.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("꼬상")
        val bettingMoney = BettingMoney(1000.0)
        player.setMoney(bettingMoney)
    }

    @Test
    fun `올바르지 않은 플레이어 이름 테스트`() {
        assertThrows<IllegalArgumentException> {
            Player("이름8자이상이름인이름")
        }
    }

    @Test
    fun `올바른 플레이어 이름 테스트`() {
        val actualName = "이름8자이하"
        val player = Player(actualName)
        assertThat(player.getName()).isEqualTo(actualName)
    }

    @Test
    fun `중복 이름에 대한 검증 테스트()`() {
        val names = listOf("꼬상", "누누", "누누")
        assertThrows<IllegalArgumentException> {
            Player.checkDuplication(names)
        }
    }

    @Test
    fun `게임에서 졌을 때, 베팅 금액을 모두 잃는다`() {
        val resultWin = Result.LOSE
        val state = State.Finish.Bust
        val expected = -1000.0
        assertThat(expected).isEqualTo(player.calculateProfit(resultWin, state))
    }

    @Test
    fun `블랙잭으로 이겼을 때, 베팅 금액의 특정 배수 만큼 수익을 받는다`() {
        val resultWin = Result.WIN
        val state = State.Finish.BlackJack
        val expected = 1500.0
        assertThat(expected).isEqualTo(player.calculateProfit(resultWin, state))
    }

    @Test
    fun `블랙잭은 아니지만 딜러보다 카드 점수가 높아서 이겼을 때, 베팅 금액의 특정 배수 만큼 수익을 받는다`() {
        val resultWin = Result.WIN
        val state = State.Finish.Stay
        val expected = 1000.0
        assertThat(expected).isEqualTo(player.calculateProfit(resultWin, state))
    }

    @Test
    fun `무승부 일 때, 베팅 금액을 돌려받는다`() {
        val resultWin = Result.DRAW
        val state = State.Finish.Stay
        val expected = 0.0
        assertThat(expected).isEqualTo(player.calculateProfit(resultWin, state))
    }
}
