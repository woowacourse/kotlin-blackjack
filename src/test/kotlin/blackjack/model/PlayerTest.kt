package blackjack.model

import model.Card
import model.CardDeck
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
        val cardDeck = CardDeck(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER))
        val player = Player(Name("jason"))
        player.drawFirst(cardDeck)
        assertThat(player.cards.cards).isEqualTo(setOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)))
    }

    @Test
    fun `플레이어의 처음 보여지는 카드는 두 장이다 `() {
        val cardDeck = CardDeck(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLOVER))
        val player = Player("jason")
        player.drawFirst(cardDeck)
        val expected = setOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLOVER))
        assertThat(player.getFirstOpenCards().cards).isEqualTo(expected)
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 카드를 뽑을 수 있는 상태이다`() {
        val player = Player("jason")
        player.cards.add(Card(Rank.KING, Suit.DIAMOND))
        player.cards.add(Card(Rank.JACK, Suit.CLOVER))
        player.cards.add(Card(Rank.ACE, Suit.HEART))
        assertThat(player.isPossibleDrawCard()).isTrue
    }

    @Test
    fun `카드의 합이 21이 넘으면 bust이다`() {
        val player = Player("jason")
        player.cards.add(Card(Rank.KING, Suit.DIAMOND))
        player.cards.add(Card(Rank.JACK, Suit.CLOVER))
        player.cards.add(Card(Rank.JACK, Suit.HEART))
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `딜러와 비교하여 점수가 딜러보다 높으면 승리한다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.TEN, Suit.DIAMOND)
        )
        val dealer = Dealer()
        val player = Player(Name("jason"))
        dealer.drawFirst(cardDeck)
        player.drawFirst(cardDeck)
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.WIN)
    }

    @Test
    fun `점수가 딜러와 같다면 패배한다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.SEVEN, Suit.HEART),
            Card(Rank.TEN, Suit.SPADE)
        )
        val dealer = Dealer()
        val player = Player(Name("jason"))
        dealer.drawFirst(cardDeck)
        player.drawFirst(cardDeck)
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `점수가 딜러와 상관 없이 Bust라면 패배한다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.HEART),
            Card(Rank.DEUCE, Suit.SPADE)
        )
        val dealer = Dealer()
        val player = Player(Name("jason"))
        dealer.drawFirst(cardDeck)
        player.drawFirst(cardDeck)
        player.cards.add(cardDeck.drawCard())
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어가 21점 이하고 딜러가 Bust라면 승리한다`() {
        val cardDeck = CardDeck(
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.DEUCE, Suit.SPADE),
            Card(Rank.TEN, Suit.HEART),
            Card(Rank.TEN, Suit.SPADE)
        )
        val dealer = Dealer()
        val player = Player(Name("jason"))
        dealer.drawFirst(cardDeck)
        dealer.cards.add(cardDeck.drawCard())
        player.drawFirst(cardDeck)
        assertThat(player.getGameResult(dealer)).isEqualTo(Result.WIN)
    }

    companion object {
        private fun Player(name: String): Player = Player(Name(name))
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
