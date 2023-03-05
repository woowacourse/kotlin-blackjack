package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {

    private lateinit var dealer: Dealer

    @BeforeEach
    private fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러는 이름을 가진다`() {
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러는 카드를 받아서 패에 추가할 수 있다`() {
        dealer.receiveCard(Card(CardShape.HEART, CardNumber.ACE))
        assertThat(dealer.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러는 처음에 Hit 상태이다`() {
        assertThat(dealer.gameState).isEqualTo(GameState.HIT)
    }

    @CsvSource(value = ["ACE,TEN,21", "TWO,THREE,5"])
    @ParameterizedTest
    fun `카드 패의 총합을 계산한다`(number1: CardNumber, number2: CardNumber, sum: Int) {
        dealer.receiveCard(Card(CardShape.HEART, number1))
        dealer.receiveCard(Card(CardShape.HEART, number2))

        assertThat(dealer.getTotalCardNumber()).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,ACE,12", "ACE,FIVE,16", "ACE, KING,21"])
    @ParameterizedTest
    fun `카드가 두 장일 때 ACE 중 하나를 무조건 11 로 간주한다`(number1: CardNumber, number2: CardNumber, sum: Int) {
        dealer.receiveCard(Card(CardShape.HEART, number1))
        dealer.receiveCard(Card(CardShape.HEART, number2))

        assertThat(dealer.getTotalCardNumber()).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,TEN,KING,21", "ACE,TWO,KING,13", "ACE,ACE,KING,12"])
    @ParameterizedTest
    fun `카드가 세 장일 때 ACE 하나를 11로 간주해서 21보다 커지면 ACE 를 모두 1로 간주한다`(
        number1: CardNumber,
        number2: CardNumber,
        number3: CardNumber,
        sum: Int,
    ) {
        dealer.receiveCard(Card(CardShape.HEART, number1))
        dealer.receiveCard(Card(CardShape.HEART, number2))
        dealer.receiveCard(Card(CardShape.HEART, number3))

        assertThat(dealer.getTotalCardNumber()).isEqualTo(sum)
    }

    @Test
    fun `딜러는 카드를 한 장만 보여준다`() {
        dealer.receiveCard(Card(CardShape.HEART, CardNumber.KING))
        dealer.receiveCard(Card(CardShape.HEART, CardNumber.QUEEN))
        val cards: List<Card> = dealer.showOneCard()

        assertThat(cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러는 카드의 총합이 17이상이면 STAND 상태가 된다`() {
        dealer.receiveCard(Card(CardShape.HEART, CardNumber.KING))
        dealer.receiveCard(Card(CardShape.HEART, CardNumber.QUEEN))

        assertThat(dealer.gameState).isEqualTo(GameState.STAND)
    }
}
