package domain.card

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardsTest {
    private lateinit var cards: Cards

    @BeforeEach
    fun setUp() {
        cards = Cards()
    }

    @CsvSource(value = ["ACE,TEN,21", "TWO,THREE,5"])
    @ParameterizedTest
    fun `카드 패의 총합을 계산한다`(number1: CardNumber, number2: CardNumber, sum: Int) {
        cards.add(Card(CardShape.HEART, number1))
        cards.add(Card(CardShape.HEART, number2))

        Assertions.assertThat(cards.getTotalCardNumber()).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,ACE,12", "ACE,FIVE,16", "ACE, KING,21"])
    @ParameterizedTest
    fun `카드가 두 장일 때 ACE 중 하나를 무조건 11 로 간주한다`(number1: CardNumber, number2: CardNumber, sum: Int) {
        cards.add(Card(CardShape.HEART, number1))
        cards.add(Card(CardShape.HEART, number2))

        Assertions.assertThat(cards.getTotalCardNumber()).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,TEN,KING,21", "ACE,TWO,KING,13", "ACE,ACE,KING,12"])
    @ParameterizedTest
    fun `카드가 세 장일 때 ACE 하나를 11로 간주해서 21보다 커지면 ACE 를 모두 1로 간주한다`(
        number1: CardNumber,
        number2: CardNumber,
        number3: CardNumber,
        sum: Int,
    ) {
        cards.add(Card(CardShape.HEART, number1))
        cards.add(Card(CardShape.HEART, number2))
        cards.add(Card(CardShape.HEART, number3))

        Assertions.assertThat(cards.getTotalCardNumber()).isEqualTo(sum)
    }
}
