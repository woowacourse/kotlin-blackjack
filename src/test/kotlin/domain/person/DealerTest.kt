package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape.CLOVER
import domain.card.CardShape.DIAMOND
import domain.card.CardShape.HEART
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
        dealer = Dealer(Card(HEART, CardNumber.KING), Card(DIAMOND, CardNumber.TWO))
    }

    @Test
    fun `딜러는 카드를 받아서 패에 추가할 수 있다`() {
        dealer.toNextState(Card(HEART, CardNumber.ACE))
        assertThat(dealer.showHandOfCards().size).isEqualTo(3)
    }

    @Test
    fun `딜러는 카드를 전부 보여줄 수 있다`() {
        val actual: List<Card> = dealer.showHandOfCards()

        assertAll(
            { assertThat(actual.size).isEqualTo(2) },
            { assertThat(actual).isEqualTo(listOf(Card(HEART, CardNumber.KING), Card(DIAMOND, CardNumber.TWO))) },
        )
    }

    @CsvSource(value = ["ACE,13", "KING,22"])
    @ParameterizedTest
    fun `카드 패의 총합을 계산한다`(number1: CardNumber, sum: Int) {
        dealer.toNextState(Card(HEART, number1))

        assertThat(dealer.getTotal()).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,false", "KING,true"])
    @ParameterizedTest
    fun `딜러의 카드 총 합이 21을 넘었는지 체크할 수 있다`(n1: CardNumber, expected: Boolean) {
        // given
        dealer.toNextState(Card(CLOVER, n1))

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
            { assertThat(actual).isEqualTo(listOf(Card(HEART, CardNumber.KING))) },
        )
    }
}
