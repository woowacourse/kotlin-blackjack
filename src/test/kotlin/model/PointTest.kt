package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `포인트의 대소 비교가 가능하다`() {
        assertThat(Point(15) > Point(14)).isTrue
        assertThat(Point(15) < Point(16)).isTrue
        assertThat(Point(15) == Point(15)).isTrue
    }
}