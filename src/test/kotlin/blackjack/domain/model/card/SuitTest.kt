package blackjack.domain.model.card

import blackjack.view.SuitTranslator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.Locale

class SuitTest {
    @ParameterizedTest
    @CsvSource(
        "0, 스페이드",
        "1, 하트",
        "2, 다이아몬드",
        "3, 클로버",
    )
    fun `카드 문양에 해당하는 문자열을 알려준다`(
        rawSuitIndex: Int,
        expectedSuitName: String,
    ) {
        // given
        val suit = Suit.getBySuitIndex(rawSuitIndex)
        val card = Card(Number.ACE, suit)

        // when
        val actualSuitName =
            SuitTranslator.localize(
                card.suit,
                Locale.KOREAN,
            )

        // then
        assertThat(actualSuitName).isEqualTo(expectedSuitName)
    }
}
