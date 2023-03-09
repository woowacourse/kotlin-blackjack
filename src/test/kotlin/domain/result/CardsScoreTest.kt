package domain.result

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Cards
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardsScoreTest {
    @CsvSource(value = ["ACE,TEN,21", "TWO,THREE,5"])
    @ParameterizedTest
    fun `카드 패의 최소 총합은 A를 모두 1로 간주하여 계산한다`(number1: CardNumber, number2: CardNumber, sum: Int) {
        val cards = Cards(
            listOf(
                Card(CardShape.HEART, number1),
                Card(CardShape.HEART, number2),
            ),
        )

        Assertions.assertThat(CardsScore.getTotalWithOneBigAce(cards)).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,ACE,12", "ACE,FIVE,16", "ACE, KING,21"])
    @ParameterizedTest
    fun `카드가 두 장일 떄 ACE 중 하나를 무조건 11 로 간주하여 총합을 계산`(number1: CardNumber, number2: CardNumber, sum: Int) {
        val cards = Cards(
            listOf(
                Card(CardShape.HEART, number1),
                Card(CardShape.HEART, number2),
            ),
        )
        Assertions.assertThat(CardsScore.getTotalWithOneBigAce(cards)).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,TEN,KING,21", "ACE,TWO,KING,13", "ACE,ACE,KING,12"])
    @ParameterizedTest
    fun `카드가 세 장일 때 ACE 하나를 11로 간주해서 총합이 21보다 크면 ACE 를 모두 1로 간주하여 총합을 계산`(
        number1: CardNumber,
        number2: CardNumber,
        number3: CardNumber,
        sum: Int,
    ) {
        // given
        val cards = Cards(
            listOf(
                Card(CardShape.HEART, number1),
                Card(CardShape.HEART, number2),
            ),
        )

        // when
        cards.add(Card(CardShape.HEART, number3))

        // then
        Assertions.assertThat(CardsScore.getTotalWithOneBigAce(cards)).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,TWO,THREE,16", "ACE,FOUR,SIX,21"])
    @ParameterizedTest
    fun `카드가 세 장일 때 ACE 하나를 11로 간주해서 총합이 21보다 작으면 그 값이 총합이다`(
        number1: CardNumber,
        number2: CardNumber,
        number3: CardNumber,
        sum: Int,
    ) {
        // given
        val cards = Cards(
            listOf(
                Card(CardShape.HEART, number1),
                Card(CardShape.HEART, number2),
            ),
        )

        // when
        cards.add(Card(CardShape.HEART, number3))

        Assertions.assertThat(CardsScore.getTotalWithOneBigAce(cards)).isEqualTo(sum)
    }
}
