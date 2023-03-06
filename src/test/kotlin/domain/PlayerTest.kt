package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
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
                Card(CardShape.CLUBS, CardValue.FIVE),
                Card(CardShape.DIAMONDS, CardValue.THREE),
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
        val dealer: Player = Dealer(cards = cards)
        // when
        val actual = dealer.validPlayerSum()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideTwoAceCards")
    @ParameterizedTest
    fun `A가 두 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer(cards = cards)
        // when
        val actual = dealer.validPlayerSum()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideThreeAceCards")
    @ParameterizedTest
    fun `A가 세 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer: Player = Dealer(cards = cards)
        // when
        val actual = dealer.validPlayerSum()
        // then
        Assertions.assertThat(actual).isEqualTo(sum)
    }

    companion object {
        private val ACE = Card(CardShape.DIAMONDS, CardValue.ACE)

        @JvmStatic
        fun provideOneAceCards(): List<Arguments> = listOf(
            Arguments.of(
                listOf(
                    ACE,
                    Card(CardShape.DIAMONDS, CardValue.NINE),
                    Card(CardShape.DIAMONDS, CardValue.FIVE),
                ),
                15,
            ),
            Arguments.of(
                listOf(
                    Card(CardShape.DIAMONDS, CardValue.TWO),
                    Card(CardShape.DIAMONDS, CardValue.THREE),
                    ACE,
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    Card(CardShape.DIAMONDS, CardValue.THREE),
                    Card(CardShape.DIAMONDS, CardValue.JACK),
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
                    Card(CardShape.DIAMONDS, CardValue.TEN),
                    ACE,
                ),
                12,
            ),
            Arguments.of(
                listOf(
                    ACE,
                    Card(CardShape.DIAMONDS, CardValue.THREE),
                    ACE,
                ),
                15,
            ),
            Arguments.of(
                listOf(
                    ACE,
                    ACE,
                    Card(CardShape.DIAMONDS, CardValue.FOUR),
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
