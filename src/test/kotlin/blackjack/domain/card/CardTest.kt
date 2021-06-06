package blackjack.domain.card

import blackjack.domain.gamer.Score
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    @DisplayName("카드 생성")
    internal fun create() {
        val symbol: Symbol = Symbol.DIAMOND
        val value: Value = Value.QUEEN
        val diamondQueen = Card(symbol, value)

        Assertions.assertThat(diamondQueen.symbol.symbol).isEqualTo("다이아몬드")
        Assertions.assertThat(diamondQueen.value.valueName).isEqualTo("Q")
        Assertions.assertThat(diamondQueen.value.score).isEqualTo(Score(10))
    }
}
