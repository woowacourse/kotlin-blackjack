package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.deck.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.IllegalArgumentException

class DeckTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setDeck() {
        testDeck = Deck(Card.getAllCard())
    }

    @Test
    fun `draw를 하면 덱에서 카드가 한 장 빠진다`() {
        val expect = testDeck.getSize()
        testDeck.draw()
        val actual = testDeck.getSize()
        assertThat(expect - 1).isEqualTo(actual)
    }

    @Test
    fun `덱의 요소의 개수보다 더 뽑게 되면 IllegalArgumentException를 던진다 `() {
        assertThrows<IllegalArgumentException> {
            repeat(testDeck.getSize() + 1) { testDeck.draw() }
        }
    }

    @Test
    fun `draw로 카드를 뽑으면 뽑은 카드는 덱에 존재하지 않는다`() {
        val card1 = testDeck.draw()
        repeat(51) {
            assertThat(testDeck.draw()).isNotEqualTo(card1)
        }
    }

    @Test
    fun `카드의 개수가 52개가 아닌 값을 전달하면 IllegalArgumentException가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Deck(Card.getAllCard().subList(0, 10))
        }
    }

    @Test
    fun `중복된 요소의 카드를 전달하면 IllegalArgumentException가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val list = Card.getAllCard().toMutableList()
            list[0] = Card.of(Rank.TWO, Suit.SPADE)
            Deck(list)
        }
    }
}
