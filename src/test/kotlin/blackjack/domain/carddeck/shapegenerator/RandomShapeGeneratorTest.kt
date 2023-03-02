package blackjack.domain.carddeck.shapegenerator

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomShapeGeneratorTest {
    @Test
    fun `랜덤으로 뽑은 값이 Shape의 value 속한다`() {
        val shape = Shape.values()
        val pickedShape = RandomShapeGenerator().pickShape()
        assertThat(shape).contains(pickedShape)
    }
}
