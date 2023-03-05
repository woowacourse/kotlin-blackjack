package blackjack.model

import domain.CardPackGenerator
import domain.CardPicker
import model.Card
import model.Cards
import model.Name
import model.Player
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드 두 장을 받을 수 있다`() {
        val cards = CardPackGenerator().createCardPack()
        val cardPicker = CardPicker(cards)
        val card = buildList {
            add(cardPicker.pick())
            add(cardPicker.pick())
        }

        val player = Player(Cards(card), Name("jason"))
        assertThat(player.cards.cards).isEqualTo(listOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)))
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 bust이다`() {
        val player = Player(
            Cards(
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
            Cards(
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
            Cards(
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
            Cards(
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
