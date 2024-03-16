package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드 뭉치에 A 스페이드 카드가 있을 때 카드를 한 장 뽑는다면 A 스페이드이다`() {
        val deck = Deck(listOf(Card(CardNumber.Ace, Suit.Spade)))
        val player = Player("cheolsoo")
        player.pickCard(deck)
        val actual = player.showCard()[0]
        val expected = Card(CardNumber.Ace, Suit.Spade)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 뭉치에 충분한 카드가 없을 경우 Deck이 모자라다는 예외를 내보낸다`() {
        val deck = Deck(listOf(Card(CardNumber.Ace, Suit.Spade)))
        val player = Player("cheolsoo")
        val expected = "덱을 모두 사용했습니다."
        lateinit var actual: String
        runCatching {
            player.pickCard(deck)
            player.pickCard(deck)
        }.onFailure {
            actual = it.message!!
        }
        assertThat(actual).isEqualTo(expected)
    }
    private fun Participant.pickCard(deck: Deck) {
        this.addCard(deck.pop())
    }
}
