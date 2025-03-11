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
    fun `카드를 뽑을 수 있다`() {
        val expect = testDeck.getSize()
        testDeck.draw()
        val actual = testDeck.getSize()
        assertThat(expect - 1).isEqualTo(actual)
    }

    @Test
    fun `덱에 카드가 없는 경우, 카드를 뽑을 수 없다`() {
        assertThrows<IllegalArgumentException> {
            repeat(testDeck.getSize() + 1) { testDeck.draw() }
        }
    }

    @Test
    fun `뽑은 카드는 덱에 존재하지 않는다`() {
        val card1 = testDeck.draw()
        repeat(51) {
            assertThat(testDeck.draw()).isNotEqualTo(card1)
        }
    }

    @Test
    fun `덱의 사이즈가 52개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Deck(Card.getAllCard().subList(0, 10))
        }
    }

    @Test
    fun `카드는 중복될 수 없습니다`() {
        assertThrows<IllegalArgumentException> {
            val list = Card.getAllCard().toMutableList()
            list[0] = Card.of(Rank.TWO, Suit.SPADE)
            Deck(list)
        }
    }
}
