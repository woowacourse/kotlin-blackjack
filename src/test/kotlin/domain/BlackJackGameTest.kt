package domain

import blackjack.domain.BetAmount
import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardHand
import blackjack.domain.CardNumber
import blackjack.domain.CardPack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.Shape
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `딜러에게 나눠준 카드 중 1장과, 플레이어들에게 나눠준 카드 두장씩 보여준다`() {
        val dealer = createDealer(CardNumber.Q, CardNumber.A)
        val player1 = createPlayer(CardNumber.A, CardNumber.EIGHT)
        val player2 = createPlayer(CardNumber.FOUR, CardNumber.TWO)
        val actual = "딜러와 player,player에게 2장의 카드를 나누었습니다.\n" +
            "딜러 카드: Q스페이드\n" +
            "player 카드: A스페이드,8스페이드\n" +
            "player 카드: 4스페이드,2스페이드"

        val blackJackGame = BlackJackGame(CardPack(), dealer, listOf(player1, player2))
        // assertThat(blackJackGame.showDividingCards(OutputView::printCardDividingMessage)).isEqualTo(actual)
    }

    private fun createDealer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Dealer {
        return Dealer(CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))))
    }

    private fun createPlayer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Player {
        return Player(
            PlayerName("player"),
            CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))),
            BetAmount(1000)
        )
    }
}
