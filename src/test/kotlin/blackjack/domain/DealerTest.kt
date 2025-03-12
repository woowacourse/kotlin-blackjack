package blackjack.domain

import blackjack.model.BlackjackEngine
import blackjack.model.Card
import blackjack.model.CardRank
import blackjack.model.CardSuit
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerTest {
    @Test
    fun `딜러가 카드를 다 뽑고나면 점수는 16점을 초과하거나 버스트이다`() {
        // given
        val blackjackEngine = BlackjackEngine()
        val dealer = blackjackEngine.prepareDealer()

        // when
        dealer.drawUntilFinished(blackjackEngine.cardDeck)

        // then
        assertThat(dealer.hand.score() > 16 || dealer.hand.isBust()).isTrue()
    }

    @Test
    fun `딜러 점수가 16 이전까지 뽑은 카드의 장수를 반환한다`() {
        // given
        val blackjackEngine = BlackjackEngine()
        val dealer = blackjackEngine.prepareDealer()
        assertThat(dealer.hand.cards.size).isEqualTo(2)
        // when
        dealer.drawUntilFinished(blackjackEngine.cardDeck)

        // then

        assertEquals(dealer.getAdditionalDrawCount(), dealer.getHandSize() - 2)
    }

    @Test
    fun `딜러 점수와 플레이어 점수 리스트를 비교하여 승패 결과를 반환한다`() {
        // given
        val dealer = Dealer(
            hand = Hand(
                listOf(
                    Card.getCashed(CardRank.TWO, CardSuit.CLUB),
                    Card.getCashed(CardRank.THREE, CardSuit.CLUB)
                )
            )
        )

        // when
        val losePlayer1 = Player(
            "패배",
            Hand(listOf(Card.getCashed(CardRank.TWO, CardSuit.CLUB), Card.getCashed(CardRank.TWO, CardSuit.CLUB)))
        )
        val losePlayer2 = Player(
            "패배",
            Hand(listOf(Card.getCashed(CardRank.TWO, CardSuit.CLUB), Card.getCashed(CardRank.TWO, CardSuit.CLUB)))
        )
        val pushPlayer = Player(
            "동점",
            Hand(listOf(Card.getCashed(CardRank.TWO, CardSuit.CLUB), Card.getCashed(CardRank.THREE, CardSuit.CLUB)))
        )
        val winningPlayer = Player(
            "승리",
            Hand(listOf(Card.getCashed(CardRank.TWO, CardSuit.CLUB), Card.getCashed(CardRank.ACE, CardSuit.CLUB)))
        )
        val players = Players(listOf(losePlayer1, losePlayer2, winningPlayer, pushPlayer))

        // then
        assertAll(
            { assertEquals(dealer.getWinDrawLossResult(players)[WinningResult.WIN], 2) },
            { assertEquals(dealer.getWinDrawLossResult(players)[WinningResult.LOSE], 1) },
            { assertEquals(dealer.getWinDrawLossResult(players)[WinningResult.PUSH], 1) }
        )

    }
}
