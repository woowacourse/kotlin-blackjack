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

class PlayerTest {

    private lateinit var player: Player

    @BeforeEach
    private fun setUp() {
        player = Player("베르", Card(HEART, CardNumber.TWO), Card(DIAMOND, CardNumber.TWO))
    }

    @Test
    fun `플레이어는 카드를 받아서 패에 추가할 수 있다`() {
        player.toNextState(Card(HEART, CardNumber.ACE))
        assertThat(player.showHandOfCards().size).isEqualTo(3)
    }

    @Test
    fun `플레이어는 카드를 전부 보여줄 수 있다`() {
        val actual: List<Card> = player.showHandOfCards()

        assertAll(
            { assertThat(actual.size).isEqualTo(2) },
            { assertThat(actual).isEqualTo(listOf(Card(HEART, CardNumber.TWO), Card(DIAMOND, CardNumber.TWO))) },
        )
    }

    @CsvSource(value = ["ACE,SIX,21", "TWO,THREE,9"])
    @ParameterizedTest
    fun `카드 패의 총합을 계산한다 - 최적의 방법으로 계산`(number1: CardNumber, number2: CardNumber, sum: Int) {
        player.toNextState(Card(CLOVER, number1))
        player.toNextState(Card(CLOVER, number2))

        assertThat(player.getTotal()).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,TEN,15", "TWO,THREE,9"])
    @ParameterizedTest
    fun `카드 패의 총합을 계산한다 - 최소한으로 계산`(number1: CardNumber, number2: CardNumber, sum: Int) {
        player.toNextState(Card(CLOVER, number1))
        player.toNextState(Card(CLOVER, number2))

        assertThat(player.getTotal()).isEqualTo(sum)
    }

    @CsvSource(value = ["ACE,ACE,false", "KING,QUEEN,true"])
    @ParameterizedTest
    fun `플레이어의 카드 총 합이 21을 넘었는지 체크할 수 있다`(n1: CardNumber, n2: CardNumber, expected: Boolean) {
        // given
        player.toNextState(Card(CLOVER, n1))
        player.toNextState(Card(CLOVER, n2))

        // when
        val actual = player.isBust()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("베르")
    }

    @CsvSource(value = ["THREE,THREE,true", "KING,QUEEN,false"])
    @ParameterizedTest
    fun `플레이어는 카드의 총합이 21초과인지 체크한다 (카드를 더 받을 수 있는지 체크한다)`(n1: CardNumber, n2: CardNumber, expected: Boolean) {
        // given
        player.toNextState(Card(HEART, n1))
        player.toNextState(Card(HEART, n2))

        // when
        val actual = player.isInProgress()

        assertThat(actual).isEqualTo(expected)
    }
}
