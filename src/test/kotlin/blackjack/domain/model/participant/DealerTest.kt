package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.CLUB_FIVE
import blackjack.domain.model.card.CLUB_KING
import blackjack.domain.model.card.CLUB_SEVEN
import blackjack.domain.model.card.CLUB_SIX
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.DIAMOND_QUEEN
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.HEART_ACE
import blackjack.domain.model.card.HEART_QUEEN
import blackjack.domain.model.card.HEART_TWO
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.SPADE_ACE
import blackjack.domain.model.card.SPADE_JACK
import blackjack.domain.model.card.SPADE_KING
import blackjack.domain.model.card.SPADE_TEN
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 첫 손패를 보여줄 수 있다`() {
        val dealerHand =
            Hand.of(
                HEART_ACE,
                CLUB_KING,
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualFirstCard = dealer.showFirstHand()

        val expectedCard = listOf(Card.of(CardNumber.ACE, Suit.HEART))

        assertThat(actualFirstCard).isEqualTo(expectedCard)
    }

    @Test
    fun `플레이어의 손패가 22점이고, 딜러의 손패가 25점이면(둘 다 버스트) 딜러가 승리한다`() {
        val deck =
            Deck {
                listOf(
                    // Player Starts
                    SPADE_KING,
                    CLUB_KING,
                    HEART_TWO,
                    // Dealer Starts
                    SPADE_JACK,
                    CLUB_FIVE,
                    DIAMOND_QUEEN,
                )
            }
        val player =
            Player("크림").apply {
                val drawnCards = deck.pop(3)
                drawnCards?.forEach(hand::add)
            }
        val dealer =
            Dealer("딜러").apply {
                val drawnCards = deck.pop(3)
                drawnCards?.forEach(hand::add)
            }

        val actualResult = dealer.compareTo(player)

        val expectedResult = GameResult.WIN

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어의 손패가 21점이고, 딜러의 손패가 15점이면 딜러가 패배한다`() {
        val deck =
            Deck {
                listOf(
                    // Player Starts
                    SPADE_KING,
                    CLUB_KING,
                    HEART_ACE,
                    // Dealer Starts
                    SPADE_JACK,
                    CLUB_FIVE,
                )
            }
        val player =
            Player("크림").apply {
                val drawnCards = deck.pop(3)
                drawnCards?.forEach(hand::add)
            }
        val dealer =
            Dealer("딜러").apply {
                val drawnCards = deck.pop(2)
                drawnCards?.forEach(hand::add)
            }

        val actualResult = dealer.compareTo(player)

        val expectedResult = GameResult.LOSE

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어의 손패가 블랙잭이고, 딜러의 손패도 블랙잭이면 무승부다`() {
        val deck =
            Deck {
                listOf(
                    // Player Starts
                    SPADE_KING,
                    HEART_ACE,
                    // Dealer Starts
                    SPADE_ACE,
                    CLUB_KING,
                )
            }
        val player =
            Player("크림").apply {
                val drawnCards = deck.pop(2)
                drawnCards?.forEach(hand::add)
            }
        val dealer =
            Dealer("딜러").apply {
                val drawnCards = deck.pop(2)
                drawnCards?.forEach(hand::add)
            }

        val actualResult = dealer.compareTo(player)

        val expectedResult = GameResult.DRAW

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어의 손패가 버스트고, 딜러의 손패도 버스트면 딜러가 승리한다`() {
        val deck =
            Deck {
                listOf(
                    // Player Starts
                    SPADE_KING,
                    HEART_QUEEN,
                    HEART_TWO,
                    // Dealer Starts
                    SPADE_TEN,
                    CLUB_SIX,
                    DIAMOND_QUEEN,
                )
            }
        val player =
            Player("크림").apply {
                val drawnCards = deck.pop(3)
                drawnCards?.forEach(hand::add)
            }
        val dealer =
            Dealer("딜러").apply {
                val drawnCards = deck.pop(3)
                drawnCards?.forEach(hand::add)
            }

        val actualResult = dealer.compareTo(player)

        val expectedResult = GameResult.WIN

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `딜러 카드의 총합이 16보다 작으면 카드를 더 받을 수 있다`() {
        val dealerHand =
            Hand.of(
                SPADE_KING,
                CLUB_SIX,
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualIsDrawable = dealer.isDrawable()

        val expected = true

        assertThat(actualIsDrawable).isEqualTo(expected)
    }

    @Test
    fun `딜러 카드의 총합이 17 이상이면 카드를 더 받을 수 없다`() {
        val dealerHand =
            Hand.of(
                SPADE_KING,
                CLUB_SEVEN,
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualIsDrawable = dealer.isDrawable()

        val expected = false

        assertThat(actualIsDrawable).isEqualTo(expected)
    }
}
