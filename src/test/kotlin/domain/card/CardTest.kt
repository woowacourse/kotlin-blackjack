package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드의 적혀있는 문자를 블랙잭의 숫자값으로 돌려준다`() {
        val card = Card(Shape.HEART, "A")

        val actual = card.valueOf()

        assertThat(actual).isEqualTo(11)
    }
}
