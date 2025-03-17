package blackjack.domain

import blackjack.model.BlackjackEngine
import blackjack.view.InputView
import blackjack.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드를 다 뽑고나면 점수는 16점을 초과하거나 버스트이다`() {
        // given
        val eventProvider = InputView()
        val eventListener = OutputView()
        val blackjackEngine = BlackjackEngine(eventProvider = eventProvider, eventListener = eventListener)
        val dealer = blackjackEngine.prepareDealer()

        // when
        dealer.drawUntilFinished(blackjackEngine.cardDeck)

        // then
        assertThat(dealer.items.hand.score() > 16 || dealer.items.hand.isBust()).isTrue()
    }
}
