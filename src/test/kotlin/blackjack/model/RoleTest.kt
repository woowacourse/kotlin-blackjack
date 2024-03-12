package blackjack.model

import blackjack.model.card.CardHand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoleTest {
    @Test
    fun `처음 카드를 2장 받는다`() {
        val dealer = Dealer(CardHand(emptyList()))
        val player = Player(PlayerName("해나"), CardHand(emptyList()))

        dealer.addInitialCards()
        player.addInitialCards()

        assertThat(dealer.cardHand.hand.size).isEqualTo(2)
        assertThat(player.cardHand.hand.size).isEqualTo(2)
    }
}
