package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("숫자와 모양을 포함한 카드를 생성한다")
    @Test
    fun createCard() {
        val newCard = Card(NumberType.ACE, ShapeType.CLOVER)
        assertThat(newCard).isNotNull
    }

    @DisplayName("숫자와 모양이 일치하는 카드는 동일한 것으로 본다")
    @Test
    fun equalsCard() {
        val newCard1 = Card(NumberType.ACE, ShapeType.CLOVER)
        val newCard2 = Card(NumberType.ACE, ShapeType.CLOVER)

        assertThat(newCard1).isEqualTo(newCard2)
    }
}
