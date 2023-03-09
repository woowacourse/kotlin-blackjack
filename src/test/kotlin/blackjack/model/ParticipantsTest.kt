package blackjack.model

import model.Card
import model.CardDeck
import model.Dealer
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
        val dealer = Dealer()
        val player1 = Player.from("jason")
        val player2 = Player.from("pobi")
        val actual = Participants(dealer, Players(player1, player2))
        assertThat(actual.all.size).isEqualTo(3)
        assertThat(actual.dealer.name.value).isEqualTo("딜러")
        assertThat(actual.players[0].name.value).isEqualTo("jason")
        assertThat(actual.players[1].name.value).isEqualTo("pobi")
    }

    @Test
    fun `참가자들은 초기 카드 두 장을 받을 수 있다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.ACE, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player.from("jason")
        val player2 = Player.from("pobi")
        val actual = Participants(dealer, Players(player1, player2))
        actual.drawFirstCard(cardDeck)
        assertThat(actual.dealer.cards.size == 2).isTrue
        assertThat(actual.players[0].cards.size == 2).isTrue
        assertThat(actual.players[1].cards.size == 2).isTrue
    }

    companion object {
        private fun Players(vararg player: Player): Players = Players(player.toList())
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
