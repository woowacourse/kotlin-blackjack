package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.Name
import model.Player
import model.Rank
import model.Result
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드 두 장을 받을 수 있다`() {
        val cards = Cards(setOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)))
        val player = Player(cards, Name("jason"))
        assertThat(player.cards.cards).isEqualTo(setOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)))
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 카드를 뽑을 수 있는 상태이다`() {
        val player = Player(
            "jason",
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.JACK, Suit.CLOVER),
            Card(Rank.ACE, Suit.HEART),
        )
        assertThat(player.isPossibleDrawCard()).isTrue
    }

    @Test
    fun `카드의 합이 21이 넘으면 bust이다`() {
        val player = Player(
            "jason",
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.JACK, Suit.CLOVER),
            Card(Rank.JACK, Suit.HEART),
        )
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `딜러와 비교하여 점수가 딜러보다 높으면 승리한다`() {
        val dealer = Dealer(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER))
        val player = Player("jason", Card(Rank.ACE, Suit.CLOVER), Card(Rank.TEN, Suit.DIAMOND))
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.WIN)
    }

    @Test
    fun `점수가 딜러와 같다면 패배한다`() {
        val dealer = Dealer(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER))
        val player = Player("jason", Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER))
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `점수가 딜러와 상관 없이 Bust라면 패배한다`() {
        val dealer = Dealer(Card(Rank.SEVEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER))
        val player = Player("jason", Card(Rank.TEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER), Card(Rank.DEUCE, Suit.SPADE))
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어가 21점 이하고 딜러가 Bust라면 승리한다`() {
        val dealer = Dealer(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER), Card(Rank.DEUCE, Suit.SPADE))
        val player = Player("jason", Card(Rank.TEN, Suit.DIAMOND), Card(Rank.TEN, Suit.CLOVER))
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.WIN)
    }

    companion object {
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
        private fun Player(name: String, vararg card: Card): Player = Player(Cards(card.toSet()), Name(name))
    }
}
