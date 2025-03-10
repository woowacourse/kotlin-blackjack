package blackjack.domain

import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 플레이어에게 카드를 나눠준다`() {
        val eden = Player("Eden")
        val gio = Player("Gio").apply { draw(Card(NumberRank.SEVEN, Suit.DIAMOND)) }
        val dealer = Dealer(listOf(eden, gio), RandomShuffler)
        dealer.pitch()
        assertThat(eden.cards.size).isEqualTo(1)
        assertThat(gio.cards.size).isEqualTo(2)
    }

    @Test
    fun `딜러는 플레이어가 카드를 더 뽑도록 할 수 있다`() {
        val player = Player("Player")
        val dealer = Dealer(listOf(player), FakeShuffler)
        dealer.giveCard(player)
        assertThat(player.cards.size).isEqualTo(1)
    }
}
