package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.Name
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어의 참가자 정보를 생성할 수 있다`() {
        val dealer = Dealer(Cards(setOf(Card(Rank.KING, Suit.HEART))))
        val players = Players(
            Player("jason", Card(Rank.ACE, Suit.HEART)),
            Player("pobi", Card(Rank.DEUCE, Suit.HEART))
        )
        val actual = Participants(listOf(dealer) + players)
        assertThat(actual.participants.size).isEqualTo(3)
        assertThat(actual.participants[0].isDealer()).isTrue
        assertThat(actual.participants[1].name.value).isEqualTo("jason")
        assertThat(actual.participants[2].name.value).isEqualTo("pobi")
    }

    companion object {
        private fun Player(name: String, vararg card: Card): Player = Player(Cards(card.toSet()), Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
    }
}
