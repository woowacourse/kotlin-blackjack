import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 들고있는 카드의 총합이 16이하라면 카드를 추가로 받는다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardNumber.TEN, Shape.DIAMOND),
                    Card(CardNumber.FIVE, Shape.SPADE)
                )
            )
        )

        dealer.drawCard()

        assertThat(
            dealer.cards.cards.size
        ).isEqualTo(3)
    }

    @Test
    fun `딜러는 들고있는 카드의 총합이 17이상이라면 카드를 추가로 받지 못한다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardNumber.TEN, Shape.DIAMOND),
                    Card(CardNumber.SEVEN, Shape.SPADE)
                )
            )
        )

        dealer.drawCard()

        // TODO: 중복 이름 해결
        assertThat(
            dealer.cards.cards.size
        ).isEqualTo(2)
    }

    @Test
    fun `특정 점수를 넘으면 false를 반환한다(17점)`() {
        assertThat(
            Dealer(
                Cards(
                    listOf(
                        Card(CardNumber.K, Shape.HEART),
                        Card(CardNumber.SEVEN, Shape.SPADE)
                    )
                )
            ).isPossibleToDraw()
        ).isFalse()
    }
}
