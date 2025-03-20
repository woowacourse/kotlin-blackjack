package blackjack.domain.study

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CastingListTest {
    @Test
    fun `데이터 클래스 List 변경 테스트`() {
        val sample = Sample(listOf(1, 2))
        val castMutableList = sample.value as MutableList
        assertThatThrownBy { castMutableList.add(1) }
    }

    class Sample(val value: List<Int>)
}
