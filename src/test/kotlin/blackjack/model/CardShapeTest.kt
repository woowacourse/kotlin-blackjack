package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardShapeTest {
    @ParameterizedTest
    @CsvSource(value = ["CLOVER, 클로버", "HEART, 하트", "DIAMOND, 다이아몬드", "SPADE, 스페이드"])
    fun `카드 모양은 한국 이름을 가진다`(
        cardShape: CardShape,
        koreanName: String,
    ) {
        val actual = cardShape.koreanName

        assertThat(actual).isEqualTo(koreanName)
    }
}
