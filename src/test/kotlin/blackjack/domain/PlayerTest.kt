package blackjack.domain

import blackjack.model.card.CardDeck
import blackjack.model.participant.Name
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `처음 생성된 참가자는 카드를 2장씩 뽑는다`() {
        // given
        val player = Player.create(Name("시아"))
        val cardDeck = CardDeck()
        val expectedSize = 2

        // when
        player.recieveCards(cardDeck::draw)

        // then
        assertThat(player.cards.size).isEqualTo(expectedSize)
    }
}
