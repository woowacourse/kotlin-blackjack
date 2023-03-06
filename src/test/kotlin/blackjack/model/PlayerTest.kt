package blackjack.model

import domain.CardPackGenerator
import model.Card
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
    fun `플레이어는 배팅 금액을 가진다`() {
        val player = Player(Hand(emptyList()), Name("jason"))
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 bust이다`() {
        val player = Player(
            Hand(
                listOf(
                    Card(Rank.KING, Suit.DIAMOND),
                    Card(Rank.JACK, Suit.CLOVER),
                    Card(Rank.ACE, Suit.HEART),
                ),
            ),
            Name("jason"),
        )
        assertThat(player.isBust()).isFalse
    }

    @Test
    fun `카드의 합이 21이 넘으면 bust이다`() {
        val player = Player(
            Hand(
                listOf(
                    Card(Rank.KING, Suit.DIAMOND),
                    Card(Rank.JACK, Suit.CLOVER),
                    Card(Rank.JACK, Suit.HEART),
                ),
            ),
            Name("jason"),
        )
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하지 않으면 hit 한다`() {
        val player = Player(
            Hand(
                listOf(
                    Card(Rank.ACE, Suit.DIAMOND),
                    Card(Rank.JACK, Suit.CLOVER),
                ),
            ),
            Name("jason"),
        )
        assertThat(player.isHit()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하면 stay 한다`() {
        val player = Player(
            Hand(
                listOf(
                    Card(Rank.ACE, Suit.DIAMOND),
                    Card(Rank.ACE, Suit.CLOVER),
                    Card(Rank.JACK, Suit.CLOVER),
                ),
            ),
            Name("jason"),
        )
        assertThat(player.isHit()).isFalse
    }
}
