package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape.CLOVER
import domain.card.CardShape.DIAMOND
import domain.card.CardShape.HEART
import domain.card.HandOfCards
import domain.card.strategy.SumStrategy.getAppropriateSum
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {

    private lateinit var dealer: Dealer

    @BeforeEach
    private fun setUp() {
        dealer = Dealer(HandOfCards(Card(HEART, CardNumber.TWO), Card(DIAMOND, CardNumber.TWO)))
    }

    @Test
    fun `딜러는 카드를 받아서 패에 추가할 수 있다`() {
        dealer.receiveCard(Card(HEART, CardNumber.ACE))
        assertThat(dealer.showHandOfCards().size).isEqualTo(3)
    }

    @Test
    fun `딜러는 카드를 전부 보여줄 수 있다`() {
        val actual: List<Card> = dealer.showHandOfCards()

        assertAll(
            { assertThat(actual.size).isEqualTo(2) },
            { assertThat(actual).isEqualTo(listOf(Card(HEART, CardNumber.TWO), Card(DIAMOND, CardNumber.TWO))) },
        )
    }

    @CsvSource(value = ["ACE,SIX,21", "TWO,THREE,9"])
    @ParameterizedTest
    fun `카드 패의 총합을 계산한다`(number1: CardNumber, number2: CardNumber, sum: Int) {
        dealer.receiveCard(Card(HEART, number1))
        dealer.receiveCard(Card(HEART, number2))

        assertThat(dealer.getTotalCardNumber { getAppropriateSum() }).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,ACE,false", "KING,QUEEN,true"])
    @ParameterizedTest
    fun `딜러의 카드 총 합이 21을 넘었는지 체크할 수 있다`(n1: CardNumber, n2: CardNumber, expected: Boolean) {
        // given
        dealer.receiveCard(Card(CLOVER, n1), Card(CLOVER, n2))

        // when
        val actual = dealer.isBust()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 이름을 가진다`() {
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러는 카드를 한 장만 보여줄 수 있다`() {
        val actual: List<Card> = dealer.showFirstCard()

        assertAll(
            { assertThat(actual.size).isEqualTo(1) },
            { assertThat(actual).isEqualTo(listOf(Card(HEART, CardNumber.TWO))) },
        )
    }

    @CsvSource(value = ["THREE,THREE,true", "KING,QUEEN,false"])
    @ParameterizedTest
    fun `딜러는 카드의 총합이 17이상인지 체크한다 (카드를 더 받을 수 있는지 체크한다)`(n1: CardNumber, n2: CardNumber, expected: Boolean) {
        // given
        dealer.receiveCard(Card(HEART, n1))
        dealer.receiveCard(Card(HEART, n2))

        // when
        val actual = dealer.canReceiveMoreCard()

        assertThat(actual).isEqualTo(expected)
    }
}
