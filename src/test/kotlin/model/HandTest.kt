package model

import fixture.SPADE_ACE
import fixture.SPADE_KING
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
            hand.add(SPADE_TWO)
        }
    }

    @Test
    fun `카드를 한장 추가할 경우 핸드의 사이즈는 1 증가한다`() {
        val size = hand.cards.size
        hand.add(SPADE_TWO)
        assertThat(hand.cards.size).isEqualTo(size + 1)
    }

    @Test
    fun `핸드 내의 카드 값의 합을 구할 수 있다`() {
        hand.add(SPADE_TWO)
        hand.add(SPADE_THREE)
        assertThat(hand.getPoint()).isEqualTo(Point(5))
    }

    @Test
    fun `핸드 내의 카드 합이 11 이하이면서 에이스가 있다면 10점이 추가된다`() {
        hand.add(SPADE_KING)
        hand.add(SPADE_ACE)

        assertThat(hand.getPoint()).isEqualTo(Point(21))
    }

    @Test
    fun `핸드 내의 카드 합이 12 이상이면 에이스는 1점으로 계산된다`() {
        hand.add(SPADE_KING)
        hand.add(SPADE_ACE)
        hand.add(SPADE_TWO)

        assertThat(hand.getPoint()).isEqualTo(Point(13))
    }
}
