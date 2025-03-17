package blackjack.domain

import blackjack.model.BlackjackEngine
import blackjack.model.Card
import blackjack.model.CardRank
import blackjack.model.CardSuit
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.WinningResult
import blackjack.view.InputView
import blackjack.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerTest {
    @Test
    fun `딜러가 카드를 다 뽑고나면 점수는 16점을 초과하거나 버스트이다`() {
        // given
        val eventProvider = InputView()
        val eventListener = OutputView()
        val blackjackEngine = BlackjackEngine(eventProvider = eventProvider, eventListener = eventListener)
        val dealer = blackjackEngine.prepareDealer()

        // when
        dealer.drawUntilFinished(blackjackEngine.cardDeck)

        // then
        assertThat(dealer.items.hand.score() > 16 || dealer.items.hand.isBust()).isTrue()
    }

    @Test
    fun `딜러 점수와 플레이어 점수 리스트를 비교하여 승패 결과를 반환한다`() {
        // given
        val dealer =
            Dealer.makeDealer(
                Hand(
                    listOf(
                        Card.getCashed(CardRank.TWO, CardSuit.CLUB),
                        Card.getCashed(CardRank.THREE, CardSuit.CLUB),
                    ),
                ),
            )

        // when
        val losePlayer =
            Player.makePlayer(
                "패배",
                Hand(listOf(Card.getCashed(CardRank.TWO, CardSuit.CLUB), Card.getCashed(CardRank.TWO, CardSuit.CLUB))),
            )
        val pushPlayer =
            Player.makePlayer(
                "동점",
                Hand(
                    listOf(
                        Card.getCashed(CardRank.TWO, CardSuit.CLUB),
                        Card.getCashed(CardRank.THREE, CardSuit.CLUB),
                    ),
                ),
            )
        val winningPlayer =
            Player.makePlayer(
                "승리",
                Hand(listOf(Card.getCashed(CardRank.TWO, CardSuit.CLUB), Card.getCashed(CardRank.ACE, CardSuit.CLUB))),
            )

        // then
        assertAll(
            { assertEquals(dealer.compareHand(winningPlayer), WinningResult.LOSE) },
            { assertEquals(dealer.compareHand(pushPlayer), WinningResult.PUSH) },
            { assertEquals(dealer.compareHand(losePlayer), WinningResult.WIN) },
        )
    }
}
