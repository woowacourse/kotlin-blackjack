package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandStatusTest {
    @ParameterizedTest
    @CsvSource(value = ["21, 2, BLACKJACK", "20, 2, NONE", "21, 3, NONE", "22, 4, BUST"])
    fun `카드들의 점수와 카드의 사이즈를 받아서 카드들의 상태를 반환한다`(
        cardsScore: Int,
        cardsSize: Int,
        expected: CardsStatus,
    ) {
        val actual = CardsStatus.from(cardsScore, cardsSize)

        assertThat(actual).isEqualTo(expected)
    }
}
