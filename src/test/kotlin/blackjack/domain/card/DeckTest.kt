package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `고유한 52장의 카드를 가지고 있어야 한다`() {
        val deck = Deck()

        deck.cards.size shouldBe 52
    }

    @Test
    fun `덱에서 카드를 뽑을 수 있다`() {
        val deck = Deck()

        deck.draw()
        deck.cards.size shouldBe 51
    }

    @Test
    fun `덱에 남은 카드가 없을 때 드로우를 할 수 없다`() {
        shouldThrowExactly<IllegalArgumentException> {
            val deck = Deck()
            repeat(53) { deck.draw() }
        }
    }
}
