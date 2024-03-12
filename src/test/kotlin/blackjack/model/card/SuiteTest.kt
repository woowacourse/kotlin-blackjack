package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class SuiteTest {
    @ValueSource(strings = ["하드", "heart", "다이어몬드", "diamond", "스폐이드", "clover"])
    @ParameterizedTest
    fun `유효하지 않은 모양일 경우 null을 반환한다`(suite: String) {
        val actual = Suite.from(suite)
        Assertions.assertNull(actual)
    }

    @MethodSource("유효한 카드 모양 테스트 데이터")
    @ParameterizedTest
    fun `유효한 카드 모양일 경우 알맞는 객체를 반환한다`(
        suite: String,
        expected: Suite,
    ) {
        val actual = Suite.from(suite)
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `유효한 카드 모양 테스트 데이터`() =
            listOf(
                Arguments.of("하트", Suite.HEART),
                Arguments.of("다이아몬드", Suite.DIAMOND),
                Arguments.of("클로버", Suite.CLOVER),
                Arguments.of("스페이드", Suite.SPADE),
            )
    }
}
