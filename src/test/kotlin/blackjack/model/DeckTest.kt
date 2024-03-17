package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DeckTest {
    @Test
    fun `히트를 하면 덱에서 한장 가져온다`() {
        // given
        val cards = mutableListOf(createCard(), createCard(), createCard(), createCard())
        val deck = Deck(cards)
        // when
        val actual = deck.pull()
        // then
        assertThat(actual).isEqualTo(createCard())
    }

    @Test
    fun `덱에 카드가 다 떨어져도 덱을 리필해서 게임이 계속 진행된다`() {
        // given
        val cards = listOf(createCard())
        val deck = Deck(cards)
        // then
        assertDoesNotThrow {
            repeat(2) {
                deck.pull()
            }
        }
    }
}
