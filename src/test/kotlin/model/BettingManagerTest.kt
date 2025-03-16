package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import util.TestCards

class BettingManagerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        val cards =
            listOf(
                TestCards.DIAMOND_KING,
                TestCards.DIAMOND_QUEEN,
            )

        player = Player("joy", Hand(cards))
    }

    @Test
    fun `플레이어는 원하는 만큼 베팅할 수 있다`() {
        val bettingManager = BettingManager()
        val bettingAmount = 10000

        assertDoesNotThrow { bettingManager.placeBet(player, bettingAmount) }
    }

    @Test
    fun `플레이어는 0원 이하로 베팅할 수 없다`() {
        val bettingManager = BettingManager()
        val bettingAmount = 0

        assertThrows<IllegalArgumentException> { bettingManager.placeBet(player, bettingAmount) }
    }

    @Test
    fun `플레이어는 원하는 값을 받을 수 있다`() {
        val bettingManager = BettingManager()
        val bettingAmount = 10000
        bettingManager.placeBet(player, bettingAmount)

        assertThat(bettingManager.getProfit(player).toInt()).isEqualTo(bettingAmount)
    }
}
