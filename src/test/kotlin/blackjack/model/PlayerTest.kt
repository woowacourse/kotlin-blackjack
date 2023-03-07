package blackjack.model

import domain.CardPackGenerator
import model.Card
import model.Dealer
import model.FinalResult
import model.Hand
import model.Name
import model.Player
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드 두 장을 받을 수 있다`() {
        val cardPack = CardPackGenerator().createCardPack()
        val card = buildList {
            add(cardPack.pop())
            add(cardPack.pop())
        }

        val player = Player(Hand(card), Name("jason"))
        assertThat(player.hand.toList()).isEqualTo(listOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)))
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 bust이다`() {
        val player = player(
            Name("jason"),
            Rank.KING,
            Rank.JACK,
            Rank.ACE
        )
        assertThat(player.isBust()).isFalse
    }

    @Test
    fun `카드의 합이 21이 넘으면 bust이다`() {
        val player = player(
            Name("jason"),
            Rank.KING,
            Rank.JACK,
            Rank.DEUCE
        )
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하지 않고 블랙잭이 아니면 hit 한다`() {
        val player = player(
            Name("jason"),
            Rank.ACE,
            Rank.JACK,
        )
        assertThat(player.isHit()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하면 stay 한다`() {
        val player = player(
            Name("jason"),
            Rank.DEUCE,
            Rank.JACK,
            Rank.KING
        )
        assertThat(player.isHit()).isFalse
    }

    @Test
    fun `딜러와 플레이어가 동시에 블랙잭인 경우 무승부이다`() {
        val player = player(
            Name("jason"),
            Rank.ACE,
            Rank.JACK,
        )
        val dealer = dealer(
            Rank.ACE,
            Rank.JACK,
        )
        assertThat(player.judge(dealer)).isEqualTo(FinalResult.PUSH)
    }

    @Test
    fun `딜러가 블랙잭이 아니고 플레이어가 블랙잭인 경우 승리한다`() {
        val player = player(
            Name("jason"),
            Rank.ACE,
            Rank.JACK,
        )
        val dealer = dealer(
            Rank.ACE,
            Rank.NINE,
        )
        assertThat(player.judge(dealer)).isEqualTo(FinalResult.BLACKJACK_WIN)
    }

    @Test
    fun `딜러 버스트, 플레이어는 버스트가 아닌 경우 승리한다`() {
        val player = player(
            Name("jason"),
            Rank.NINE,
            Rank.DEUCE,
            Rank.EIGHT
        )
        val dealer = dealer(
            Rank.DEUCE,
            Rank.JACK,
            Rank.KING
        )
        assertThat(player.judge(dealer)).isEqualTo(FinalResult.WIN)
    }

    @Test
    fun `둘 다 버스트되지 않고 딜러보다 높은 점수인 경우 승리한다`() {
        val player = player(
            Name("jason"),
            Rank.JACK,
            Rank.KING
        )
        val dealer = dealer(
            Rank.ACE,
            Rank.DEUCE,
            Rank.KING
        )
        assertThat(player.judge(dealer)).isEqualTo(FinalResult.WIN)
    }

    @Test
    fun `둘 다 버스트되지 않고 딜러와 점수가 같은 경우 무승부이다`() {
        val player = player(
            Name("jason"),
            Rank.JACK,
            Rank.KING
        )
        val dealer = dealer(
            Rank.JACK,
            Rank.KING
        )
        assertThat(player.judge(dealer)).isEqualTo(FinalResult.PUSH)
    }

    @Test
    fun `둘 다 버스트되지 않고 딜러보다 낮은 점수인 경우 패배한다`() {
        val player = player(
            Name("jason"),
            Rank.NINE,
            Rank.KING
        )
        val dealer = dealer(
            Rank.JACK,
            Rank.KING
        )
        assertThat(player.judge(dealer)).isEqualTo(FinalResult.LOSE)
    }

    private fun player(name: Name, vararg ranks: Rank): Player = Player(
        Hand(ranks.map { Card(it, Suit.DIAMOND) }), name
    )

    private fun dealer(vararg ranks: Rank): Dealer = Dealer(
        Hand(ranks.map { Card(it, Suit.HEART) })
    )
}
