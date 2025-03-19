package blackjack.domain

import blackjack.domain.betting.BettingAmount
import blackjack.domain.betting.BettingInfo
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `게임 결과를 생성한다`() {
        // Given
        val player = Player("test", listOf(Card(CardNumber.ACE, CardPattern.CLOVER), Card(CardNumber.KING, CardPattern.SPADE)))
        val bettingInfo = BettingInfo(player, BettingAmount(10000))
        val dealer = Dealer()

        // When
        val gameResult = GameResult.create(dealer, listOf(bettingInfo))

        // Then
        assertSoftly(gameResult) {
            results[dealer]?.value shouldBe -15000.0
            results[player]?.value shouldBe 15000.0
        }
    }
}
