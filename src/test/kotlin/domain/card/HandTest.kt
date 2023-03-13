package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setUp() {
        hand = Hand(
            Card(CardShape.HEART, CardNumber.KING),
            Card(CardShape.HEART, CardNumber.TWO),
        )
    }

    @Test
    fun `카드 하나를 추가할 수 있다`() {
        hand.add(Card(CardShape.HEART, CardNumber.KING))

        assertThat(hand.value.size).isEqualTo(3)
    }

    @Test
    fun `ACE 를 가지고 있는지 확인할 수 있다`() {
        hand.add(Card(CardShape.HEART, CardNumber.ACE))

        assertThat(hand.hasAce()).isTrue
    }
}
