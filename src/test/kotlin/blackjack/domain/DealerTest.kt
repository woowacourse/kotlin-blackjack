package blackjack.domain

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드를 다 뽑고나면 점수는 16점을 초과하거나 버스트이다`() {
        // given
        val cardDeck = CardDeck()
        val dealer = Dealer()

        // when
        dealer.drawUntilFinished(cardDeck)

        // then
        assertThat(dealer.score() > 16 || dealer.isBust()).isTrue()
    }

    @Test
    fun `딜러 점수가 16 이전까지 뽑은 카드의 장수를 반환한다`() {
        // given
        val cardDeck = CardDeck()
        val dealer = Dealer()
        val initialDrawCount = 2
        dealer.draw(cardDeck)

        // when
        val dealerDrawCount = dealer.drawUntilFinished(cardDeck)

        // then
        assertThat(dealerDrawCount).isEqualTo(dealer.cards().size - initialDrawCount)
    }

    @Test
    fun `딜러 점수와 플레이어 점수 리스트를 비교하여 승패 결과를 반환한다`() {
        // given
        val cardDeck = CardDeck()
        val dealer = Dealer()
        dealer.draw(cardDeck)

        // when
        val playerScores = listOf(0, 22, 0, 22, 0, dealer.score())

        // then
        assertThat(dealer.result(playerScores)[WinningResult.WIN]).isEqualTo(3)
        assertThat(dealer.result(playerScores)[WinningResult.LOSE]).isEqualTo(2)
        assertThat(dealer.result(playerScores)[WinningResult.PUSH]).isEqualTo(1)
    }
}
