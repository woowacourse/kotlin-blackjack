package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 하나에는 조커를 제외한 모든 트럼프 카드 52장이 들어있다`() {
        val deck = Deck()
        val oneDeck =
            listOf(
                Card(CardNumber.Ace, Suit.Heart),
                Card(CardNumber.Ace, Suit.Club),
                Card(CardNumber.Ace, Suit.Spade),
                Card(CardNumber.Ace, Suit.Diamond),
                Card(CardNumber.Two, Suit.Heart),
                Card(CardNumber.Two, Suit.Club),
                Card(CardNumber.Two, Suit.Spade),
                Card(CardNumber.Two, Suit.Diamond),
                Card(CardNumber.Three, Suit.Heart),
                Card(CardNumber.Three, Suit.Club),
                Card(CardNumber.Three, Suit.Spade),
                Card(CardNumber.Three, Suit.Diamond),
                Card(CardNumber.Four, Suit.Heart),
                Card(CardNumber.Four, Suit.Club),
                Card(CardNumber.Four, Suit.Spade),
                Card(CardNumber.Four, Suit.Diamond),
                Card(CardNumber.Five, Suit.Heart),
                Card(CardNumber.Five, Suit.Club),
                Card(CardNumber.Five, Suit.Spade),
                Card(CardNumber.Five, Suit.Diamond),
                Card(CardNumber.Six, Suit.Heart),
                Card(CardNumber.Six, Suit.Club),
                Card(CardNumber.Six, Suit.Spade),
                Card(CardNumber.Six, Suit.Diamond),
                Card(CardNumber.Seven, Suit.Heart),
                Card(CardNumber.Seven, Suit.Club),
                Card(CardNumber.Seven, Suit.Spade),
                Card(CardNumber.Seven, Suit.Diamond),
                Card(CardNumber.Eight, Suit.Heart),
                Card(CardNumber.Eight, Suit.Club),
                Card(CardNumber.Eight, Suit.Spade),
                Card(CardNumber.Eight, Suit.Diamond),
                Card(CardNumber.Nine, Suit.Heart),
                Card(CardNumber.Nine, Suit.Club),
                Card(CardNumber.Nine, Suit.Spade),
                Card(CardNumber.Nine, Suit.Diamond),
                Card(CardNumber.Ten, Suit.Heart),
                Card(CardNumber.Ten, Suit.Club),
                Card(CardNumber.Ten, Suit.Spade),
                Card(CardNumber.Ten, Suit.Diamond),
                Card(CardNumber.Jack, Suit.Heart),
                Card(CardNumber.Jack, Suit.Club),
                Card(CardNumber.Jack, Suit.Spade),
                Card(CardNumber.Jack, Suit.Diamond),
                Card(CardNumber.Queen, Suit.Heart),
                Card(CardNumber.Queen, Suit.Club),
                Card(CardNumber.Queen, Suit.Spade),
                Card(CardNumber.Queen, Suit.Diamond),
                Card(CardNumber.King, Suit.Heart),
                Card(CardNumber.King, Suit.Club),
                Card(CardNumber.King, Suit.Spade),
                Card(CardNumber.King, Suit.Diamond),
            )
        val player = Player("cheolsoo")
        repeat(52) {
            deck giveCardTo player
        }
        val actual = player.showCard().toSet().intersect(oneDeck.toSet()).size
        assertThat(actual).isEqualTo(52)
    }
}
