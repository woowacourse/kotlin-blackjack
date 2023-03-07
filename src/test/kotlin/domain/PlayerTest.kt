package domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.Shape
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {

    @Test
    fun `딜러는 A와 J를 들고 있다`() {
        // given
        val dealer: Player = Dealer.create(
            cards = mutableListOf<Card>(
                Card.of(Shape.CLUBS, CardNumber.ACE),
                Card.of(Shape.DIAMONDS, CardNumber.JACK),
            ),
        )

        // when
        val actual = dealer.addScoreTenIfHasAce()

        // then
        Assertions.assertThat(actual).isEqualTo(21)
    }

    @MethodSource("provideOneAceCards")
    @ParameterizedTest
    fun `A가 한 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer.create(cards)
        // when
        val actual = dealer.addScoreTenIfHasAce()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideTwoAceCards")
    @ParameterizedTest
    fun `A가 두 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer.create(cards)
        // when
        val actual = dealer.addScoreTenIfHasAce()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideThreeAceCards")
    @ParameterizedTest
    fun `A가 세 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer.create(cards)
        // when
        val actual = dealer.addScoreTenIfHasAce()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    companion object {
        private val ACE = Card.of(Shape.DIAMONDS, CardNumber.ACE)

        @JvmStatic
        fun provideOneAceCards(): List<Arguments> = listOf(
            Arguments.of(
                listOf(
                    ACE,
                    Card.of(Shape.DIAMONDS, CardNumber.NINE),
                    Card.of(Shape.DIAMONDS, CardNumber.FIVE),
                ),
                15,
            ),
            Arguments.of(
                listOf(
                    Card.of(Shape.DIAMONDS, CardNumber.TWO),
                    Card.of(Shape.DIAMONDS, CardNumber.THREE),
                    ACE,
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    Card.of(Shape.DIAMONDS, CardNumber.THREE),
                    Card.of(Shape.DIAMONDS, CardNumber.JACK),
                    ACE,
                ),
                14,
            ),
        )

        @JvmStatic
        fun provideTwoAceCards(): List<Arguments> = listOf(
            Arguments.of(
                listOf(
                    ACE,
                    Card.of(Shape.DIAMONDS, CardNumber.TEN),
                    ACE,
                ),
                12,
            ),
            Arguments.of(
                listOf(
                    ACE,
                    Card.of(Shape.DIAMONDS, CardNumber.THREE),
                    ACE,
                ),
                15,
            ),
            Arguments.of(
                listOf(
                    ACE,
                    ACE,
                    Card.of(Shape.DIAMONDS, CardNumber.FOUR),
                ),
                16,
            ),
        )

        @JvmStatic
        fun provideThreeAceCards(): List<Arguments> = listOf(
            Arguments.of(
                listOf(
                    ACE,
                    ACE,
                    ACE,
                ),
                13,
            ),
        )
    }
}
