package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 지급된 카드를 가진다`() {
        val card = Card.of(Rank.ACE, Suit.SPADE)
        val player = Player()
        player.addCard(card)
        assertThat(player.cards).contains(card)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 없음)`() {
        val player = Player()

        val card1 = Card.of(Rank.TWO, Suit.SPADE)
        val card2 = Card.of(Rank.THREE, Suit.SPADE)

        player.addCard(card1)
        player.addCard(card2)

        assertThat(player.totalSum).isEqualTo(5)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 1장)`() {
        val player = Player()

        val card1 = Card.of(Rank.TWO, Suit.SPADE)
        val card2 = Card.of(Rank.ACE, Suit.SPADE)

        player.addCard(card1)
        player.addCard(card2)

        assertThat(player.totalSum).isEqualTo(13)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 2장)`() {
        val player = Player()

        val card1 = Card.of(Rank.ACE, Suit.SPADE)
        val card2 = Card.of(Rank.NINE, Suit.SPADE)
        val card3 = Card.of(Rank.ACE, Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.totalSum).isEqualTo(21)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(버스트)`() {
        val player = Player()

        val card1 = Card.of(Rank.ACE, Suit.SPADE)
        val card2 = Card.of(Rank.NINE, Suit.SPADE)
        val card3 = Card.of(Rank.NINE, Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.totalSum).isEqualTo(19)
    }
}
