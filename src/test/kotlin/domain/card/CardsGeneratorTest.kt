package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsGeneratorTest {

    @Test
    fun `카드 52장을 생성한다`() {
        assertThat(CardsGenerator().createCards()).hasSize(52)
    }
}
