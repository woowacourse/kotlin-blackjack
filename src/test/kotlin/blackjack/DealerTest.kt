package blackjack

import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape
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

    @Test
    fun `딜러 카드의 총합이 21을 초과하면 버스트 된다`() {
        val dealer = Dealer()
        repeat(3) {
            dealer.addCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(dealer.isBust()).isEqualTo(true)
    }
}
