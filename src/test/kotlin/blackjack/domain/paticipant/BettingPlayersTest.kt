package blackjack.domain.paticipant

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.ParticipantCards
import blackjack.domain.participant.BettingPlayers
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class BettingPlayersTest {

    @Test
    fun `플레이어는 최소 1명이어야 한다`() {
        val players = emptyList<Player>()

        assertThatIllegalArgumentException().isThrownBy { BettingPlayers(players, emptyMap()) }
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

        assertThatIllegalArgumentException().isThrownBy { BettingPlayers(players, emptyMap()) }
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
        val player1 = Player("부나")
        val player2 = Player("글로")
        val player3 = Player("반달")
        val bettingPlayers = BettingPlayers(
            listOf(player1, player2, player3),
            mapOf(player1 to 0, player2 to 0, player3 to 0)
        )

        bettingPlayers.drawAll(deck)
        bettingPlayers.drawAll(deck)

        val actual = bettingPlayers.getFirstOpenCards()
        assertThat(actual).isEqualTo(
            listOf(
                ParticipantCards("부나", listOf(Card(CardNumber.ACE, Suit.SPADE), Card(CardNumber.KING, Suit.SPADE))),
                ParticipantCards("글로", listOf(Card(CardNumber.ACE, Suit.HEART), Card(CardNumber.TWO, Suit.SPADE))),
                ParticipantCards("반달", listOf(Card(CardNumber.ACE, Suit.DIAMOND), Card(CardNumber.EIGHT, Suit.SPADE)))
            )
        )
    }
}
