package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows

internal class CardDeckTest {

    @DisplayName("Deck에서 임의의 카드를 추출한다.")
    @Test
    fun draw() {
        val deck = CardDeck()

        val picked = deck.draw()
        assertThat(deck.contains(picked)).isFalse
    }

    @DisplayName("덱에 카드가 없는 상황에서 draw를 호출하면 에러를 발생한다.")
    @Test
    fun testEmptyDeck() {
        val deck = CardDeck()
        val numberOfCardInDeck = deck.size()

        for (i in 0 until numberOfCardInDeck) {
            deck.draw()
        }

        assertThrows<IllegalArgumentException> {  deck.draw() }
    }
}