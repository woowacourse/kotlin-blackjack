package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DealerTest {
    @Test
    fun `딜러가 소지한 카드의 합은 8이다`() {
        // given
        val dealer = Dealer(
            mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
            ),
        )

        // when
        val actual = dealer.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `2장의 합이 16이하일 경우, 카드 1장을 추가로 받는다`() {
        // given
        val dealer = Dealer(
            mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
            ),
        )
        val newCard = CardMachine().getNewCard()

        // when
        dealer.addCard(newCard)
        val actual = dealer.cards

        // then
        assertThat(actual).hasSize(3)
    }

    @Test
    fun `2장의 합이 17이상인 경우, 카드를 받지 않는다`() {
        // given
        val dealer = Dealer(
            mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.JACK),
                Card(Card.Shape.DIAMONDS, Card.Value.EIGHT),
            ),
        )

        // when
        dealer.isOverSumCondition()
        val actual = dealer.cards

        // then
        assertThat(actual).hasSize(2)
    }

    @MethodSource("provideOneAceCards")
    @ParameterizedTest
    fun `A가 한 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer = Dealer.create(cards)
        // when
        val actual = dealer.validDealerSum()
        // then
        assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideTwoAceCards")
    @ParameterizedTest
    fun `A가 두 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer = Dealer.create(cards)
        // when
        val actual = dealer.validDealerSum()
        // then
        assertThat(actual).isEqualTo(sum)
    }

    @MethodSource("provideThreeAceCards")
    @ParameterizedTest
    fun `A가 세 장인 경우`(cards: List<Card>, sum: Int) {
        // given
        val dealer = Dealer.create(cards)
        // when
        val actual = dealer.validDealerSum()
        // then
        assertThat(actual).isEqualTo(sum)
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
