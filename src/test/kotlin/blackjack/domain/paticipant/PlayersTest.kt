package blackjack.domain.paticipant

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.ParticipantCards
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerInfo
import blackjack.domain.participant.Players
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
            Player(PlayerInfo("a", 0)), Player(PlayerInfo("b", 0)),
            Player(PlayerInfo("c", 0)), Player(PlayerInfo("d", 0)),
            Player(PlayerInfo("e", 0)), Player(PlayerInfo("f", 0)),
            Player(PlayerInfo("g", 0)), Player(PlayerInfo("h", 0))
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
        val players = Players(
            listOf(
                Player(PlayerInfo("buna", 0)),
                Player(PlayerInfo("glo", 0)),
                Player(PlayerInfo("bandal", 0))
            )
        )

        players.drawAll(deck)
        players.drawAll(deck)

        val actual = players.getFirstOpenCards()
        assertThat(actual).isEqualTo(
            listOf(
                ParticipantCards("buna", listOf(Card(CardNumber.ACE, Suit.SPADE), Card(CardNumber.KING, Suit.SPADE))),
                ParticipantCards("glo", listOf(Card(CardNumber.ACE, Suit.HEART), Card(CardNumber.TWO, Suit.SPADE))),
                ParticipantCards("bandal", listOf(Card(CardNumber.ACE, Suit.DIAMOND), Card(CardNumber.EIGHT, Suit.SPADE)))
            )
        )
    }
}
