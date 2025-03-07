package blackjack.model.service

import blackjack.model.domain.ActionType
import blackjack.model.domain.Card
import blackjack.model.domain.CardNumber
import blackjack.model.domain.Dealer
import blackjack.model.domain.Deck
import blackjack.model.domain.ParticipantStatus
import blackjack.model.domain.Player
import blackjack.model.domain.Shape
import blackjack.model.strategy.FalseShuffle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    private val player1 = Player("제리")
    private val player2 = Player("환노")
    private val player3 = Player("포르")
    private val dealer = Dealer()
    private val deck = Deck(FalseShuffle())
    private val game = Blackjack(deck)

    @Test
    fun `게임 시작시 카드를 2장을 나눈다`() {
        game.initGame(listOf(player1, player2, dealer))

        assertThat(dealer.cardDeck.size).isEqualTo(2)
        assertThat(player1.cardDeck.size).isEqualTo(2)
        assertThat(player2.cardDeck.size).isEqualTo(2)
    }

    @Test
    fun `게임이 끝난 후 승패를 가린다`() {
        player1.receiveCard(Card(Shape.Spade, CardNumber.Ace))
        player2.receiveCard(Card(Shape.Spade, CardNumber.Six))
        player3.receiveCard(Card(Shape.Heart, CardNumber.Seven))
        dealer.receiveCard(Card(Shape.Spade, CardNumber.Seven))

        game.endGame(listOf(player1, player2, player3), dealer)

        assertThat(player1.status).isEqualTo(ParticipantStatus.Win)
        assertThat(player2.status).isEqualTo(ParticipantStatus.Lose)
        assertThat(player3.status).isEqualTo(ParticipantStatus.Draw)
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 카드를 추가로 받는다`() {
        game.initGame(listOf(player1, player2, dealer))
        game.drawUntilThreshold(dealer)
        assertThat(dealer.cardDeck.size).isGreaterThan(2)
    }

    @Test
    fun `플레이어는 stay를 외칠 시 카드를 그만 받는다`() {
        val initPlayerCardSize = player1.cardDeck.size
        game.shouldStopDrawing(ActionType.Stay, player1)
        assertThat(player1.cardDeck.size).isEqualTo(initPlayerCardSize)
    }

    @Test
    fun `플레이어는 hit을 외칠 시 카드를 한장 받는다`() {
        val initPlayerCardSize = player1.cardDeck.size
        game.shouldStopDrawing(ActionType.Hit, player1)
        assertThat(player1.cardDeck.size).isEqualTo(initPlayerCardSize + 1)
    }
}
