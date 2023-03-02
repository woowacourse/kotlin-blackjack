package blackjack

import Card
import CardPackGenerator
import CardPicker
import Cards
import Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드 두 장을 받을 수 있다`() {
        val cards = CardPackGenerator().createCards()
        val cardPicker = CardPicker(cards)
        val card = buildList {
            add(cardPicker.pick())
            add(cardPicker.pick())
        }

        val player = Player(Cards(card))
        assertThat(player.cards.cards).isEqualTo(listOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.CLOVER)))
    }
}
