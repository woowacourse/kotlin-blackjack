package blackjack.domain

import blackjack.model.BlackjackEngine
import blackjack.model.Card
import blackjack.model.CardRank
import blackjack.model.CardSuit
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        val initialDrawCount = 2
        // when
        val dealerDrawCount = dealer.drawUntilFinished(blackjackEngine.cardDeck)

        // then

        assertThat(dealerDrawCount).isEqualTo(dealer.hand.cards.size - initialDrawCount)
    }

    @Test
    fun `딜러 점수와 플레이어 점수 리스트를 비교하여 승패 결과를 반환한다`() {
        // given
        val blackjackEngine = BlackjackEngine()
        val dealer = Dealer(listOf(Card.getCard(CardRank.TWO, CardSuit.CLUB),Card.getCard(CardRank.THREE, CardSuit.CLUB)))

        // when
        val losePlayer1 = Player("패배",listOf(Card.getCard(CardRank.TWO, CardSuit.CLUB),Card.getCard(CardRank.TWO, CardSuit.CLUB)))
        val losePlayer2 = Player("패배",listOf(Card.getCard(CardRank.TWO, CardSuit.CLUB),Card.getCard(CardRank.TWO, CardSuit.CLUB)))
        val pushPlayer = Player("동점",listOf(Card.getCard(CardRank.TWO, CardSuit.CLUB),Card.getCard(CardRank.THREE, CardSuit.CLUB)))
        val winningPlayer = Player("승리",listOf(Card.getCard(CardRank.TWO, CardSuit.CLUB),Card.getCard(CardRank.ACE, CardSuit.CLUB)))
        val players = Players(listOf(losePlayer1, losePlayer2, winningPlayer, pushPlayer))

        // then
        assertThat(dealer.result(players)[WinningResult.WIN]).isEqualTo(1)
        assertThat(dealer.result(players)[WinningResult.LOSE]).isEqualTo(2)
        assertThat(dealer.result(players)[WinningResult.PUSH]).isEqualTo(1)
    }
}
