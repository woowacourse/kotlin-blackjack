package blackjack.model

import blackjack.model.amount.BetAmount
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `플레이어는 원하는 만큼 추가 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer()
        val player = Player("미플", BetAmount(100.0))
        val game = BlackjackGame(dealer, listOf(player))

        player.addCard(Card(Shape.SPADE, CardNumber.THREE))
        player.addCard(Card(Shape.SPADE, CardNumber.TWO))

        var drawCount = 0

        // when
        game.startGame(
            wantsToDraw = { if (drawCount < 2) DrawChoice.YES else DrawChoice.NO },
            onEndPlayerTurn = { drawCount++ },
            onEndDealerTurn = { _ -> },
            printAllHands = { },
        )

        // then
        assertEquals(2, drawCount, "플레이어는 최대 2장까지 뽑을 수 있어야 한다")
    }

    @Test
    fun `딜러와 플레이어의 카드 상태에 따라 게임 결과를 올바르게 계산해야 한다`() {
        // given
        val dealer = Dealer()
        val player = Player("미플", BetAmount(100.0))
        val game = BlackjackGame(dealer, listOf(player))

        player.addCard(Card(Shape.SPADE, CardNumber.THREE))
        player.addCard(Card(Shape.SPADE, CardNumber.TWO))
        dealer.addCard(Card(Shape.CLOVER, CardNumber.NINE))
        dealer.addCard(Card(Shape.DIAMOND, CardNumber.EIGHT))

        // when
        val results = game.calculateResults()

        // then
        val playerResult = results[1]
        assertEquals(-100.0, playerResult.winningMoney.amount, "플레이어가 패배하면 베팅 금액을 잃는다")
    }
}
