package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class DeckTest {
    private val deck = Deck((0..51).toList())
    @Test
    fun `카드를 뽑을 수 있다`() {
        val card1 = deck.getCard()
        assertThat(card1).isEqualTo(Card.SPADE_A)
        val card2 = deck.getCard()
        assertThat(card2).isEqualTo(Card.SPADE_2)

    }
    @Test
    fun `덱에서 카드는 최대 52번 뽑을 수 있다`(){
        assertThrows<IllegalStateException> {
            repeat(53) {deck.getCard()}
        }
    }

    @Test
    fun `뽑은 카드는 덱에 존재하지 않는다`(){
        val card1 = deck.getCard()
        assertThat(card1).isEqualTo(Card.SPADE_A)
        repeat(51) {
            assertThat(deck.getCard()).isNotEqualTo(Card.SPADE_A)
        }
    }

    @Test
    fun `덱의 사이즈는 52여야 합니다`(){
        assertThrows<IllegalArgumentException> {
           Deck((0..30).toList())
        }
    }

    @Test
    fun `카드는 중복될 수 없습니다`(){
        assertThrows<IllegalArgumentException> {
            val list = (0..51).toMutableList()
            list[0] = 1
            Deck(list)
        }
    }

    @Test
    fun `카드 번호는 0부터 51까지 입니다`(){
        assertThrows<IllegalArgumentException> {
            val list = (0..51).toMutableList()
            list[0] = 100
            Deck(list)
        }
    }
}
