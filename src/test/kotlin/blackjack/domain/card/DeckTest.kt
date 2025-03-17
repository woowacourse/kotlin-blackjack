package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `고유한 52장의 카드를 가지고 있어야 한다`() {
        shouldThrowExactly<IllegalArgumentException> {
            Deck(List(53) { Card.create(CardNumber.JACK, CardPattern.HEART) })
        }
    }

    @Test
    fun `덱에서 카드를 뽑을 수 있다`() {
        val deck = Deck()

        deck.draw()
        deck.cards.size shouldBe 51
    }

    @Test
    fun `덱에 새로운 카드가 없다면 다시 셔플된 카드를 보충한다`() {
        val deck = Deck()

        repeat(53) { deck.draw() }

        deck.cards.size shouldBe 51
    }
}
