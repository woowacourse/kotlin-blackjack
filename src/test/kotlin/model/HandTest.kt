package model

import TestDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class HandTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        hand = Hand()
        deck = TestDeck(mutableListOf(Card.from(1), Card.from(2)))
    }

    @Test
    fun `카드를 한장 추가할 수 있다`() {
        assertDoesNotThrow {
            hand.hit(deck)
        }
    }

    @Test
    fun `카드를 한장 추가할 경우 핸드의 사이즈는 1 증가한다`() {
        val size = hand.cards.size
        hand.hit(deck)

        assertThat(hand.cards.size).isEqualTo(size + 1)
    }

    @Test
    fun `핸드 내의 카드 값의 합을 구할 수 있다`() {
        hand.hit(deck)
        hand.hit(deck)
        assertThat(hand.getPoint()).isEqualTo(5)
    }
}
