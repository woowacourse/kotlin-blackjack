package domain

import blackjack.domain.BetAmount
import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardHand
import blackjack.domain.CardNumber
import blackjack.domain.CardPack
import blackjack.domain.Dealer
import blackjack.domain.DrawResult
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerName
import blackjack.domain.Shape
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `게임에 참여하는 딜러와 플레이어들의 정보를 넘긴다`() {
        val dealer = createDealer()
        val players = createPlayers(count = 3)
        val blackJackGame = BlackJackGame(CardPack(), dealer, players)

        assertThat(blackJackGame.showParticipants()).isEqualTo(Pair(dealer, players))
    }

    @Test
    fun `카드 합이 12인 딜러가 처음 2장 이외의 카드를 추가로 받았는지 확인한다`() {
        val dealer = createDealerWithCards(CardNumber.FOUR, CardNumber.EIGHT)
        val players = createPlayers(count = 3)
        val blackJackGame = BlackJackGame(CardPack(), dealer, players)

        assertThat(blackJackGame.drawAdditionalCardForDealer()).isEqualTo(DrawResult.Success)
    }

    @Test
    fun `카드 합이 17인 딜러가 처음 2장 이외의 카드를 추가로 받았는지 확인한다`() {
        val dealer = createDealerWithCards(CardNumber.NINE, CardNumber.EIGHT)
        val players = createPlayers(count = 3)
        val blackJackGame = BlackJackGame(CardPack(), dealer, players)

        assertThat(blackJackGame.drawAdditionalCardForDealer()).isEqualTo(DrawResult.Failure)
    }

    @Test
    fun `카드의 합이 17인 딜러를 상대로 카드합이 16인 플레이어는 패, 카드합이 18인 플레이어는 승`() {
        val dealer = createDealerWithCards(CardNumber.TEN, CardNumber.SEVEN)
        val player1 = createPlayerWithCards(CardNumber.SEVEN, CardNumber.NINE)
        val player2 = createPlayerWithCards(CardNumber.EIGHT, CardNumber.Q)
        val blackJackGame = BlackJackGame(CardPack(), dealer, listOf(player1, player2))

        val actual = listOf(PlayerGameResult(player1, GameResult.LOSE), PlayerGameResult(player2, GameResult.WIN))

        assertThat(blackJackGame.judgeGameResults().first).isEqualTo(actual)
    }

    @Test
    fun `플레이어의 결과가 '패, 승'인경우 딜러의 결과는 그 반대인 '승, 패'이다 `() {
        val dealer = createDealerWithCards(CardNumber.TEN, CardNumber.SEVEN)
        val player1 = createPlayerWithCards(CardNumber.SEVEN, CardNumber.NINE)
        val player2 = createPlayerWithCards(CardNumber.EIGHT, CardNumber.Q)
        val blackJackGame = BlackJackGame(CardPack(), dealer, listOf(player1, player2))

        val actual = listOf(GameResult.WIN, GameResult.LOSE)

        assertThat(blackJackGame.judgeGameResults().second).isEqualTo(actual)
    }

    @Test
    fun `플레이어의 베팅결과 `() {
        val dealer = createDealerWithCards(CardNumber.TEN, CardNumber.SEVEN)
        val player1 = createPlayerWithBet(CardNumber.SEVEN, CardNumber.NINE, 1000)
        val player2 = createPlayerWithBet(CardNumber.EIGHT, CardNumber.Q, 2000)
        val blackJackGame = BlackJackGame(CardPack(), dealer, listOf(player1, player2))

        val actualPlayerDividend = mapOf(Pair(player1.name, BetAmount(-1000)), Pair(player2.name, BetAmount(2000)))
        val actualDealerDividend = BetAmount(-1000)

        assertThat(blackJackGame.judgeBetResults(blackJackGame.judgeGameResults().first)).isEqualTo(Pair(actualDealerDividend, actualPlayerDividend))
    }

    private fun createDealer(): Dealer {
        return Dealer(CardHand(listOf(CardPack().draw(), CardPack().draw())))
    }

    private fun createDealerWithCards(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Dealer {
        return Dealer(CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))))
    }

    private fun createPlayers(count: Int): List<Player> {
        val players = mutableListOf<Player>()
        repeat(count) {
            players.add(Player(PlayerName("name"), CardHand(listOf(CardPack().draw(), CardPack().draw())), BetAmount(1000)))
        }
        return players
    }

    private fun createPlayerWithCards(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Player {
        return Player(PlayerName("name"), CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))), BetAmount(1000))
    }

    private fun createPlayerWithBet(firstCardNumber: CardNumber, secondCardNumber: CardNumber, money: Int): Player {
        return Player(PlayerName("name"), CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))), BetAmount(money))
    }
}
