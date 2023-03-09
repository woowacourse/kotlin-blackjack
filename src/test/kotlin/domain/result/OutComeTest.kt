package domain.result

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardNumber.ACE
import domain.card.CardNumber.FIVE
import domain.card.CardNumber.FOUR
import domain.card.CardNumber.KING
import domain.card.CardNumber.SIX
import domain.card.CardNumber.THREE
import domain.card.CardNumber.TWO
import domain.card.CardShape
import domain.card.HandOfCards
import domain.person.Dealer
import domain.person.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class OutComeTest {
    @MethodSource("provideData")
    @ParameterizedTest
    fun `getOutCome 함수가 결과값을 잘 반환하는지 테스트`(
        p1: CardNumber,
        p2: CardNumber,
        p3: CardNumber,
        d1: CardNumber,
        d2: CardNumber,
        d3: CardNumber,
        expected: Pair<String, OutCome>,
    ) {
        val player = Player(
            "빅스",
            HandOfCards(Card(CardShape.HEART, p1), Card(CardShape.DIAMOND, p2)),
        ).apply {
            receiveCard(Card(CardShape.CLOVER, p3))
        }
        val dealer =
            Dealer(HandOfCards(Card(CardShape.CLOVER, d1), Card(CardShape.SPADE, d2))).apply {
                receiveCard(Card(CardShape.DIAMOND, d3))
            }

        val actual = OutCome.getOutCome(dealer, player)

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideData(): List<Arguments> = listOf(
            Arguments.of(KING, KING, TWO, TWO, THREE, FOUR, "빅스" to OutCome.LOSE), // player is Bust
            Arguments.of(ACE, TWO, THREE, KING, KING, THREE, "빅스" to OutCome.WIN), // dealer is Bust
            Arguments.of(KING, KING, ACE, TWO, THREE, FOUR, "빅스" to OutCome.WIN), // player is Bigger
            Arguments.of(ACE, ACE, FOUR, KING, SIX, FIVE, "빅스" to OutCome.LOSE), // dealer is Bigger
            Arguments.of(TWO, THREE, SIX, THREE, FOUR, FOUR, "빅스" to OutCome.DRAW), // same
        )
    }
}
