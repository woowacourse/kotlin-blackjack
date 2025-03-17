package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import util.TestCards

class ProfitCalculatorTest {
    private lateinit var bettingManager: BettingManager
    private lateinit var profitCalculator: ProfitCalculator

    @BeforeEach
    fun setup() {
        bettingManager = BettingManager()
        profitCalculator = ProfitCalculator()
    }

    @Test
    fun `플레이어가 WIN일 때 profit을 확인할 수 있다`() {
        val player = Player("joy", Hand(listOf(TestCards.DIAMOND_KING, TestCards.DIAMOND_QUEEN)))
        val players = Players(listOf(player))

        val playerResults = listOf(PlayerResult(player, GameResult.WIN))
        bettingManager.placeBet(player, 100)

        val finalProfits = profitCalculator.calculateFinalProfits(playerResults, bettingManager, players)

        assertThat(finalProfits[player]?.amount).isEqualTo(100)
    }

    @Test
    fun `플레이어가 LOSE일 때 profit을 확인할 수 있다`() {
        val player = Player("joy", Hand(listOf(TestCards.SPADE_EIGHT, TestCards.HEART_TEN)))
        val players = Players(listOf(player))

        val playerResults = listOf(PlayerResult(player, GameResult.LOSE))
        bettingManager.placeBet(player, 100)

        val finalProfits = profitCalculator.calculateFinalProfits(playerResults, bettingManager, players)

        assertThat(finalProfits[player]?.amount).isEqualTo(-100)
    }

    @Test
    fun `플레이어가 PUSH일 때 profit을 확인할 수 있다`() {
        val player = Player("joy", Hand(listOf(TestCards.CLUB_TEN, TestCards.HEART_TEN)))
        val players = Players(listOf(player))

        val playerResults = listOf(PlayerResult(player, GameResult.PUSH))
        bettingManager.placeBet(player, 100)

        val finalProfits = profitCalculator.calculateFinalProfits(playerResults, bettingManager, players)

        assertThat(finalProfits[player]?.amount).isEqualTo(0)
    }

    @Test
    fun `플레이어가 BLACKJACK일 때 profit을 확인할 수 있다`() {
        val player = Player("joy", Hand(listOf(TestCards.CLUB_ACE, TestCards.CLUB_TEN)))
        val players = Players(listOf(player))

        val playerResults = listOf(PlayerResult(player, GameResult.BLACKJACK))
        bettingManager.placeBet(player, 100)

        val finalProfits = profitCalculator.calculateFinalProfits(playerResults, bettingManager, players)

        assertThat(finalProfits[player]?.amount).isEqualTo(150)
    }
}
