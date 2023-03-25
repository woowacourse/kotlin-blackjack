package domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.Shape
import domain.player.Dealer
import domain.player.Player
import domain.player.User
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import view.GameResult

class PlayerTest {
    @Test
    fun `딜러는 18점, 유저1, 유저2의 점수는 19, 15점이다`() {
        // given
        val player1Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.NINE),
            Card.of(Shape.DIAMONDS, CardNumber.KING),
        )

        val player2Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.QUEEN),
            Card.of(Shape.DIAMONDS, CardNumber.FIVE),
        )

        val dealer = Dealer.create(
            listOf<Card>(
                Card.of(Shape.CLUBS, CardNumber.QUEEN),
                Card.of(Shape.DIAMONDS, CardNumber.EIGHT),
            ),
        )

        val users: List<User> = listOf(
            User.create("player1", player1Cards),
            User.create("player2", player2Cards),
        )

        // when
        val actual = users.map { user -> user.getResult(dealer) }
        val expected = listOf<GameResult>(GameResult.WIN, GameResult.LOSE)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 22점, 유저1, 유저2의 점수는 19, 15점이다`() {
        // given
        val player1Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.NINE),
            Card.of(Shape.DIAMONDS, CardNumber.KING),
        )

        val player2Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.QUEEN),
            Card.of(Shape.DIAMONDS, CardNumber.FIVE),
        )

        val dealer = Dealer.create(
            listOf<Card>(
                Card.of(Shape.CLUBS, CardNumber.QUEEN),
                Card.of(Shape.DIAMONDS, CardNumber.TWO),
                Card.of(Shape.DIAMONDS, CardNumber.KING),
            ),
        )

        val users: List<User> = listOf(
            User.create("player1", player1Cards),
            User.create("player2", player2Cards),
        )

        // when
        val actual = users.map { user -> user.getResult(dealer) }
        val expected = listOf<GameResult>(GameResult.WIN, GameResult.WIN)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 22점, 유저1, 유저2의 점수는 23, 15점이다`() {
        // given
        val player1Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.NINE),
            Card.of(Shape.DIAMONDS, CardNumber.KING),
            Card.of(Shape.DIAMONDS, CardNumber.FOUR),
        )

        val player2Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.QUEEN),
            Card.of(Shape.DIAMONDS, CardNumber.FIVE),
        )

        val dealer = Dealer.create(
            listOf<Card>(
                Card.of(Shape.CLUBS, CardNumber.QUEEN),
                Card.of(Shape.DIAMONDS, CardNumber.TWO),
                Card.of(Shape.DIAMONDS, CardNumber.KING),
            ),
        )

        val users: List<User> = listOf(
            User.create("player1", player1Cards),
            User.create("player2", player2Cards),
        )

        // when
        val actual = users.map { user -> user.getResult(dealer) }
        val expected = listOf<GameResult>(GameResult.DRAW, GameResult.WIN)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러, 유저1, 유저2가 블랙잭일 경우, 무승부이다`() {
        // given
        val player1Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.ACE),
            Card.of(Shape.DIAMONDS, CardNumber.JACK),
        )

        val player2Cards: List<Card> = listOf<Card>(
            Card.of(Shape.CLUBS, CardNumber.ACE),
            Card.of(Shape.DIAMONDS, CardNumber.JACK),
        )

        val dealer = Dealer.create(
            listOf<Card>(
                Card.of(Shape.HEARTS, CardNumber.JACK),
                Card.of(Shape.SPADES, CardNumber.ACE),
            ),
        )

        val users: List<User> = listOf(
            User.create("player1", player1Cards),
            User.create("player2", player2Cards),
        )

        // when
        val actual = users.map { user -> user.getResult(dealer) }
        val expected = listOf<GameResult>(GameResult.DRAW, GameResult.DRAW)

        // then
        assertThat(actual).isEqualTo(expected)
    }

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
