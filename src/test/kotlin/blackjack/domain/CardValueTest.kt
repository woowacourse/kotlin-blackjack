package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardValueTest {

    @Test
    fun `A의 값은 1이다`() {
        assertThat(CardValue.ACE.value).isEqualTo(1)
    }

    @Test
    fun `K의 값은 10이다`() {
        assertThat(CardValue.KING.value).isEqualTo(10)
    }

    @Test
    fun `Q의 값은 10이다`() {
        assertThat(CardValue.QUEEN.value).isEqualTo(10)
    }

    @Test
    fun `J의 값은 10이다`() {
        assertThat(CardValue.JACK.value).isEqualTo(10)
    }

    @Test
    fun `TEN의 값은 10이다`() {
        assertThat(CardValue.TEN.value).isEqualTo(10)
    }

    @Test
    fun `NINE의 값은 9이다`() {
        assertThat(CardValue.NINE.value).isEqualTo(9)
    }

    @Test
    fun `EIGHT의 값은 8이다`() {
        assertThat(CardValue.EIGHT.value).isEqualTo(8)
    }

    @Test
    fun `SEVEN의 값은 7이다`() {
        assertThat(CardValue.SEVEN.value).isEqualTo(7)
    }

    @Test
    fun `SIX의 값은 6이다`() {
        assertThat(CardValue.SIX.value).isEqualTo(6)
    }

    @Test
    fun `FIVE의 값은 5이다`() {
        assertThat(CardValue.FIVE.value).isEqualTo(5)
    }

    @Test
    fun `FOUR의 값은 4이다`() {
        assertThat(CardValue.FOUR.value).isEqualTo(4)
    }

    @Test
    fun `THREE의 값은 3이다`() {
        assertThat(CardValue.THREE.value).isEqualTo(3)
    }

    @Test
    fun `TWO의 값은 2이다`() {
        assertThat(CardValue.TWO.value).isEqualTo(2)
    }
}
