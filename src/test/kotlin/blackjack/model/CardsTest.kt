package blackjack.model

import model.Card
import model.Cards
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `초기 카드들이 중복되면 에러를 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Cards(
                listOf(
                    Card(Rank.ACE, Suit.CLOVER),
                    Card(Rank.ACE, Suit.CLOVER),
                ),
            )
        }
    }

    @Test
    fun `이미 존재하는 카드를 넣으면 에러를 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Cards(
                listOf(
                    Card(Rank.ACE, Suit.CLOVER),
                    Card(Rank.ACE, Suit.DIAMOND),
                ),
            ).add(Card(Rank.ACE, Suit.DIAMOND))
        }
    }

    @Test
    fun `카드를 한 장씩 추가할 수 있다`() {
        val cards = Cards(listOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.ACE, Suit.DIAMOND)))
        cards.add(Card(Rank.DEUCE, Suit.DIAMOND))
        assertThat(cards.cards.size).isEqualTo(3)
    }

    @Test
    fun `카드의 합을 구할 수 있다`() {
        val cards = Cards(listOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.DEUCE, Suit.DIAMOND)))
        assertThat(cards.sum()).isEqualTo(13)
    }
}
