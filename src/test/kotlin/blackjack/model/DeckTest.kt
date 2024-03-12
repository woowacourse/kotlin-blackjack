package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드 뭉치에 A 스페이드 카드가 있을 때 카드를 한 장 뽑는다면 A 스페이드이다`() {
        val deck = Deck(mutableListOf(Card(CardNumber.Ace, Suit.Spade)))
        val player = Player("cheolsoo")
        deck giveCardTo player
        val actual = player.showCard()[0]
        val expected = Card(CardNumber.Ace, Suit.Spade)
        assertThat(actual).isEqualTo(expected)
    }
}
