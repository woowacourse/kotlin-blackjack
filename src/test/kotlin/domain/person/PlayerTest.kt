package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardNumber.ACE
import domain.card.CardNumber.EIGHT
import domain.card.CardNumber.FOUR
import domain.card.CardNumber.JACK
import domain.card.CardNumber.KING
import domain.card.CardNumber.QUEEN
import domain.card.CardNumber.TEN
import domain.card.CardNumber.THREE
import domain.card.CardNumber.TWO
import domain.card.CardShape.HEART
import domain.constant.GameState.BUST
import domain.constant.GameState.HIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {

    fun Player(cardNumbers: List<CardNumber>, name: String) = Player(cardNumbers.map { Card(HEART, it) }, name)

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player(listOf(ACE, TWO), "베르")
        assertThat(player.name).isEqualTo("베르")
    }

    @Test
    fun `플레이어는 카드를 받아서 패에 추가할 수 있다`() {
        // given
        val player = Player(listOf(ACE, TWO), "베르")

        // when
        player.receiveCard(listOf(Card(HEART, ACE)))

        // then
        assertThat(player.cards.value.size).isEqualTo(3)
    }

    @Test
    fun `플레이어는 처음에 Hit 상태이다`() {
        // given
        val player = Player(listOf(ACE, TWO), "베르")

        // then
        assertThat(player.isState(HIT)).isTrue
    }

    @MethodSource("provideCardsBust")
    @ParameterizedTest
    fun `ACE 를 1로 간주한 합계가 21을 넘으면 상태가 BUST 이다`(numbers: List<CardNumber>) {
        val cardNumbers = numbers.toMutableList()

        // given
        val player = Player(
            listOf(
                cardNumbers.removeFirst(),
                cardNumbers.removeFirst(),
            ),
            "베르",
        )

        // when
        cardNumbers.forEach { number -> player.receiveCard(listOf(Card(HEART, number))) }

        // then
        assertThat(player.isState(BUST)).isTrue
    }

    @MethodSource("provideCardsHit")
    @ParameterizedTest
    fun `ACE 를 1로 간주한 합계가 21 보다 작으면 상태가 HIT 이다`(numbers: List<CardNumber>) {
        val cardNumbers = numbers.toMutableList()

        // given
        val player = Player(
            listOf(
                cardNumbers.removeFirst(),
                cardNumbers.removeFirst(),
            ),
            "베르",
        )

        // when
        cardNumbers.forEach { number -> player.receiveCard(listOf(Card(HEART, number))) }

        assertThat(player.isState(HIT)).isTrue
    }

    companion object {
        @JvmStatic
        fun provideCardsBust() = listOf(
            // 22
            Arguments.of(
                listOf(KING, KING, ACE, ACE),
            ),
            // 22
            Arguments.of(
                listOf(FOUR, TEN, EIGHT),
            ),
            // 30
            Arguments.of(
                listOf(TEN, KING, QUEEN),
            ),

        )

        @JvmStatic
        fun provideCardsHit() = listOf(
            // 2
            Arguments.of(
                listOf(ACE, ACE),
            ),
            // 5
            Arguments.of(
                listOf(ACE, ACE, ACE, THREE),
            ),
            // 20
            Arguments.of(
                listOf(TEN, KING),
            ),
            // 21
            Arguments.of(
                listOf(TEN, JACK, ACE),
            ),
        )
    }
}
