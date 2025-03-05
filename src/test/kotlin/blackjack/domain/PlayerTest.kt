package blackjack.domain

import blackjack.domain.enums.Rank
import blackjack.domain.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 카드를 한 장 지급 받으면 플레이어의 패는 한 장이다`() {
        // given
        val hand = Hand(emptyList())
        val player = Player("Jason", hand)
        val card = Card(Rank.ACE, Suit.SPADE)

        // when
        player.addCard(card)

        // then
        assertThat(player.hand.cards.size).isEqualTo(1)
    }
}
