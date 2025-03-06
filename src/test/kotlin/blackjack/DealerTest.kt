package blackjack

import blackjack.domain.participant.Dealer
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 게임을 시작하면 카드를 2장 지급받는다`() {
        val dealer = Dealer()
        val fixture = trumpCardFixture()
        fixture.forEach {
            dealer.addCard(it)
        }
        assertThat(dealer.cards).containsExactly(*fixture.toTypedArray())
    }
}
