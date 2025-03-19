package blackjack.domain.betting

import blackjack.domain.participants.Player
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BettingInfoTest {
    @Test
    fun `플레이어와 배팅 금액을 받는다`() {
        // Given
        val player = Player("pobi")
        val bettingAmount = BettingAmount(10000)

        // When
        val bettingInfo = BettingInfo(player, bettingAmount)

        // Then
        assertSoftly(bettingInfo) {
            player.name shouldBe "pobi"
            bettingAmount.value shouldBe 10000
        }
    }
}
