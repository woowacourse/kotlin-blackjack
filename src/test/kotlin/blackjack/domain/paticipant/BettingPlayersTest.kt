package blackjack.domain.paticipant

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.ParticipantCards
import blackjack.domain.participant.BettingPlayer
import blackjack.domain.participant.BettingPlayers
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class BettingPlayersTest {

    @Test
    fun `플레이어는 최소 1명이어야 한다`() {
        val players = emptyList<BettingPlayer>()

        assertThatIllegalArgumentException().isThrownBy { BettingPlayers(players) }
            .withMessageContaining("블랙잭은 최소 1명에서 최대 7명의 플레이어가 참여 가능합니다. (현재 플레이어수 : 0명)")
    }

    @Test
    fun `플레이어는 최대 7명까지 가능하다`() {
        val players = listOf(
            BettingPlayer(Player("a"), 0), BettingPlayer(Player("b"), 0),
            BettingPlayer(Player("c"), 0), BettingPlayer(Player("d"), 0),
            BettingPlayer(Player("e"), 0), BettingPlayer(Player("f"), 0),
            BettingPlayer(Player("g"), 0), BettingPlayer(Player("h"), 0)
        )

        assertThatIllegalArgumentException().isThrownBy { BettingPlayers(players) }
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
        val bettingPlayers = BettingPlayers(
            listOf(
                BettingPlayer(Player("buna"), 0),
                BettingPlayer(Player("glo"), 0),
                BettingPlayer(Player("bandal"), 0)
            )
        )

        bettingPlayers.drawAll(deck)
        bettingPlayers.drawAll(deck)

        val actual = bettingPlayers.getCards()
        assertThat(actual).isEqualTo(
            listOf(
                ParticipantCards("buna", listOf(Card(CardNumber.ACE, Suit.SPADE), Card(CardNumber.KING, Suit.SPADE))),
                ParticipantCards("glo", listOf(Card(CardNumber.ACE, Suit.HEART), Card(CardNumber.TWO, Suit.SPADE))),
                ParticipantCards("bandal", listOf(Card(CardNumber.ACE, Suit.DIAMOND), Card(CardNumber.EIGHT, Suit.SPADE)))
            )
        )
    }
}
