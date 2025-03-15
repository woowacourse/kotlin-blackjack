package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱은 카드 한 장을 뽑을 수 있다`() {
        val deck = Deck()
        val drawnCard = deck.draw()
        assertThat(deck.cards.contains(drawnCard)).isFalse()
    }

    @Test
    fun `덱은 52장을 모두 뽑은 후 카드를 뽑으면 새로운 덱을 사용한다`() {
        val deck = Deck()
        repeat(53) {
            deck.draw()
        }
        assertThat(deck.cards.size).isEqualTo(51)
    }

    // 리뷰 받은 부분 확인용
//    @Test
//    fun `덱 clear`() {
//        val deck = Deck()
//        deck.cards.remove()
//        deck.cards.remove()
//        deck.cards.clear()
//        deck.pick()
//        deck.cards.remove()
//        deck.pick()
//        deck.cards.remove()
//        assertThat(deck.cards.size).isEqualTo(51)
//    }
}
