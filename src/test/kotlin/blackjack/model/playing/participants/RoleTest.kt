package blackjack.model.playing.participants

import blackjack.model.card.CardDeck
import blackjack.model.card.generator.RandomCardGenerator
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoleTest {
    @Test
    fun `처음 카드를 2장 받는다`() {
        val dealer = Dealer(CardHand(emptyList()))
        val player = Player(PlayerName("해나"), CardHand(emptyList()))
        val cardDeck = CardDeck().cardDeck

        dealer.addInitialCards(RandomCardGenerator(cardDeck))
        player.addInitialCards(RandomCardGenerator(cardDeck))

        assertThat(dealer.cardHand.hand.size).isEqualTo(2)
        assertThat(player.cardHand.hand.size).isEqualTo(2)
    }
}
