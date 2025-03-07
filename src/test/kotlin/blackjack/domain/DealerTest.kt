package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 플레이어에게 카드를 나눠준다`() {
        val eden = Player("Eden")
        val gio = Player("Gio")
        val players: List<Player> = listOf(eden, gio)
        val dealer = Dealer(players)
        gio.getCard(Card(Number(7), Suit.DIAMOND))
        dealer.giveCard()
        assertThat(eden.getCountOfCards()).isEqualTo(1)
        assertThat(gio.getCountOfCards()).isEqualTo(2)
    }
}
