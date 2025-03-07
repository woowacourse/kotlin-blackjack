package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardsStatusTest {
    @ParameterizedTest
    @CsvSource(value = ["21, true, BLACKJACK", "20, false, NONE", "21, false, NONE", "22, false, BUST"])
    fun `카드들의 점수와 처음 턴 여부를 받아서 카드들의 상태를 반환한다`(
        cardsScore: Int,
        firstTurn: Boolean,
        expected: CardsStatus,
    ) {
        val actual = CardsStatus.from(cardsScore, firstTurn)

        assertThat(actual).isEqualTo(expected)
    }
}
