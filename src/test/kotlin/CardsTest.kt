import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardsTest {

    @Test
    fun `게임을 시작할때 카드가 두장이 아니면 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Cards(listOf(Card(CardNumber.A, Shape.SPADE)))
        }
    }

    @Test
    fun `게임을 시작할때 카드가 두장이어야한다`() {
        assertDoesNotThrow {
            Cards(listOf(Card(CardNumber.A, Shape.SPADE), Card(CardNumber.A, Shape.HEART)))
        }
    }

    @Test
    fun `특정 점수를 넘으면 false를 반환한다(17점)`() {
        assertThat(
            Cards(
                listOf(
                    Card(CardNumber.K, Shape.HEART),
                    Card(CardNumber.SEVEN, Shape.SPADE)
                )
            ).isPossibleToDraw(17)
        ).isFalse()
    }

    @Test
    fun `가지고 있는 카드 숫자의 합을 구한다`() {
        assertThat(
            Cards(
                listOf(
                    Card(CardNumber.K, Shape.HEART),
                    Card(CardNumber.SEVEN, Shape.SPADE)
                )
            ).getSum()
        ).isEqualTo(17)
    }
}
