package blackjack

import blackjack.domain.Dealer
import blackjack.domain.TrumpCard
import blackjack.domain.enums.CardTier
import blackjack.domain.enums.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 게임을 시작하면 카드를 2장 지급받는다`() {
        val dealer = Dealer()
        dealer.drawCard(TrumpCard(CardTier.K, Shape.Dia))
        dealer.drawCard(TrumpCard(CardTier.Ace, Shape.Heart))
        assertThat(dealer.cards.size).isEqualTo(2)
    }
}
