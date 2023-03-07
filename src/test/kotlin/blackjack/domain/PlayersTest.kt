package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `플레이어는 최소 1명이어야 한다`() {
        val players = emptyList<Player>()

        assertThatIllegalArgumentException().isThrownBy { Players(players) }
            .withMessageContaining("블랙잭은 최소 1명에서 최대 7명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 0명)")
    }

    @Test
    fun `플레이어는 최대 7명까지 가능하다`() {
        val players = listOf(
            Player("a"), Player("b"),
            Player("c"), Player("d"),
            Player("e"), Player("f"),
            Player("g"), Player("h")
        )

        assertThatIllegalArgumentException().isThrownBy { Players(players) }
            .withMessageContaining("블랙잭은 최소 1명에서 최대 7명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 8명)")
    }

    @Test
    fun `모든 플레이어의 카드를 2장씩 뽑는다`() {
        val deck = CardDeck(
            listOf(
                Card(CardNumber.ACE, Suit.SPADE), Card(CardNumber.ACE, Suit.HEART),
                Card(CardNumber.ACE, Suit.DIAMOND), Card(CardNumber.KING, Suit.SPADE),
                Card(CardNumber.TWO, Suit.SPADE), Card(CardNumber.EIGHT, Suit.SPADE)
            )
        )
        val players = Players(listOf(Player("buna"), Player("glo"), Player("bandal")))

        players.drawAll(deck)
        players.drawAll(deck)

        val actual = players.getCards()
        assertThat(actual).isEqualTo(
            mapOf(
                "buna" to listOf(Card(CardNumber.ACE, Suit.SPADE), Card(CardNumber.KING, Suit.SPADE)),
                "glo" to listOf(Card(CardNumber.ACE, Suit.HEART), Card(CardNumber.TWO, Suit.SPADE)),
                "bandal" to listOf(Card(CardNumber.ACE, Suit.DIAMOND), Card(CardNumber.EIGHT, Suit.SPADE))
            )
        )
    }
}
