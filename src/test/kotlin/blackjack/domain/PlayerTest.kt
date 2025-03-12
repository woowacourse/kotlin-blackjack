package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player("Jason")
        dealer = Dealer()
    }

    @Test
    fun `플레이어가 카드를 한 장 지급 받으면 플레이어의 패는 한 장이다`() {
        // given
        val card = Card(Rank.ACE, Suit.SPADE)

        // when
        player.addCard(card)

        // then
        assertThat(player.hand.cards.size).isEqualTo(1)
    }
}
