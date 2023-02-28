package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardShapeTest {

    @ParameterizedTest
    @ValueSource(strings = ["스페이드", "클로버", "하트", "다이아몬드"])
    fun `카드 모양을 가진다`(expected: String) {
        val cardShape: List<String> = CardShape.values().map { it.shape }
        assertThat(cardShape).contains(expected)
    }
}
