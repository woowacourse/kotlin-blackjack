package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.CLUB_EIGHT
import blackjack.domain.model.card.CLUB_SEVEN
import blackjack.domain.model.card.CLUB_TWO
import blackjack.domain.model.card.DIAMOND_QUEEN
import blackjack.domain.model.card.HEART_ACE
import blackjack.domain.model.card.HEART_QUEEN
import blackjack.domain.model.card.HEART_TEN
import blackjack.domain.model.card.HEART_TWO
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.SPADE_JACK
import blackjack.domain.model.card.SPADE_KING
import blackjack.domain.model.card.SPADE_TEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 블랙잭이고 딜러가 18점이면 플레이어가 블랙잭 승리한다`() {
        val playerHand =
            Hand.of(
                SPADE_KING,
                HEART_ACE,
            )
        val player = Player("크림", playerHand)
        val dealerHand =
            Hand.of(
                SPADE_JACK,
                CLUB_EIGHT,
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = player.compareTo(dealer)

        val expectedResult = GameResult.BLACKJACK_WIN

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어가 12점이고 딜러가 버스트이면 플레이어가 승리한다`() {
        val playerHand =
            Hand.of(
                SPADE_KING,
                HEART_TWO,
            )
        val player = Player("크림", playerHand)
        val dealerHand =
            Hand.of(
                SPADE_JACK,
                CLUB_EIGHT,
                DIAMOND_QUEEN,
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = player.compareTo(dealer)

        val expectedResult = GameResult.WIN

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어가 버스트고 딜러가 17점이면 플레이어가 패배한다`() {
        val playerHand =
            Hand.of(
                SPADE_KING,
                HEART_QUEEN,
                DIAMOND_QUEEN,
            )
        val player = Player("크림", playerHand)
        val dealerHand =
            Hand.of(
                SPADE_JACK,
                CLUB_SEVEN,
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = player.compareTo(dealer)

        val expectedResult = GameResult.LOSE

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어 카드의 총합이 22 이상이면 카드를 더 받을 수 없다`() {
        val playerHand =
            Hand.of(
                SPADE_TEN,
                HEART_TEN,
                CLUB_TWO,
            )
        val player = Player("크림", playerHand)
        val actualIsDrawable = player.isDrawable()

        val expectedIsDrawable = false

        assertThat(actualIsDrawable).isEqualTo(expectedIsDrawable)
    }
}
