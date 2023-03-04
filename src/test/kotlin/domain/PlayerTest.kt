package domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {

    @Test
    fun `딜러가 소지한 카드의 합은 8이다`() {
        // given
        val dealer: Player = Dealer(
            cards = mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
            ),
        )

        // when
        val actual = dealer.calculateCardValueSum()

        // then
        Assertions.assertThat(actual).isEqualTo(8)
    }

    @MethodSource("provideOneAceCards")
    @ParameterizedTest
    fun `A가 한 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer.create(cards)
        // when
        val actual = dealer.validPlayerSum()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideTwoAceCards")
    @ParameterizedTest
    fun `A가 두 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer.create(cards)
        // when
        val actual = dealer.validPlayerSum()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideThreeAceCards")
    @ParameterizedTest
    fun `A가 세 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer.create(cards)
        // when
        val actual = dealer.validPlayerSum()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    companion object {
        private val ACE = Card(Card.Shape.DIAMONDS, Card.Value.ACE)

        @JvmStatic
        fun provideOneAceCards(): List<Arguments> = listOf(
            Arguments.of(
                listOf(
                    ACE,
                    Card(Card.Shape.DIAMONDS, Card.Value.NINE),
                    Card(Card.Shape.DIAMONDS, Card.Value.FIVE),
                ),
                15,
            ),
            Arguments.of(
                listOf(
                    Card(Card.Shape.DIAMONDS, Card.Value.TWO),
                    Card(Card.Shape.DIAMONDS, Card.Value.THREE),
                    ACE,
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    Card(Card.Shape.DIAMONDS, Card.Value.THREE),
                    Card(Card.Shape.DIAMONDS, Card.Value.JACK),
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
                    Card(Card.Shape.DIAMONDS, Card.Value.TEN),
                    ACE,
                ),
                12,
            ),
            Arguments.of(
                listOf(
                    ACE,
                    Card(Card.Shape.DIAMONDS, Card.Value.THREE),
                    ACE,
                ),
                15,
            ),
            Arguments.of(
                listOf(
                    ACE,
                    ACE,
                    Card(Card.Shape.DIAMONDS, Card.Value.FOUR),
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
