package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ShapeTest {

    @ParameterizedTest
    @CsvSource("스페이드,스페이드", "다이아몬드,다이아몬드", "하트,하트", "클로버,클로버")
    fun `카드 모양에는 스페이드, 다이아몬드, 하트, 클로버가 있다`(shape: Shape, expected: String) {
        assertThat(shape.name).isEqualTo(expected)
    }
}
