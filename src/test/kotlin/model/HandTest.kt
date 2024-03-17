package model

import fixture.SPADE_THREE
import fixture.SPADE_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class HandTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun handInit() {
        hand = Hand()
    }

    @Test
    fun `카드를 한장 추가할 수 있다`() {
        assertDoesNotThrow {
            hand.draw(SPADE_TWO)
        }
    }

    @Test
    fun `카드를 한장 추가할 경우 핸드의 사이즈는 1 증가한다`() {
        val size = hand.cards.size
        hand.draw(SPADE_TWO)
        assertThat(hand.cards.size).isEqualTo(size + 1)
    }

    @Test
    fun `핸드 내의 카드 값의 합을 구할 수 있다`() {
        hand.draw(SPADE_TWO)
        hand.draw(SPADE_THREE)
        assertThat(hand.getPoint()).isEqualTo(Point(5))
    }
}
