package entity

import entity.card.Card
import entity.card.CardNumber
import entity.card.CardType
import entity.card.Cards
import entity.result.BettingMoney
import entity.result.GameResultType
import entity.result.PlayersGameResult
import entity.result.UserBettingResult
import entity.users.Dealer
import entity.users.Name
import entity.users.Player
import entity.users.Players
import entity.users.UserInformation
import entity.users.Users
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserBettingResultTest {
    @Test
    fun `플레이어의 카드의 수가 2장이고 블랙잭이면 배팅금액의 1,5배를 돌려받는다`() {
        // given
        val player = Player(
            UserInformation(Name("test"), BettingMoney(1000)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )
        val players = Players(listOf(player))
        val dealer = Dealer()
        val playerGameResult = PlayersGameResult(mapOf(player to GameResultType.WIN))

        // when
        val userBettingResult = UserBettingResult().getPlayersBettingResults(Users(players, dealer), playerGameResult)
        val profitMoney = userBettingResult[player]

        // then
        assertThat(profitMoney).isEqualTo(2500.0)
    }

    @Test
    fun `플레이어가 이겼을 때 배팅금액만큼 돌려받는다`() {
        // given
        val player = Player(
            UserInformation(Name("test"), BettingMoney(1000)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.FOUR),
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.FIVE)
                )
            )
        )
        val players = Players(listOf(player))
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.SEVEN)
                )
            )
        )
        val playerGameResult = PlayersGameResult(mapOf(player to GameResultType.WIN))

        // when
        val userBettingResult = UserBettingResult().getPlayersBettingResults(Users(players, dealer), playerGameResult)
        val profitMoney = userBettingResult[player]

        // then
        assertThat(profitMoney).isEqualTo(2000.0)
    }

    @Test
    fun `플레이어와 딜러 둘 다 21일 때 플레이어가 원금을 돌려받는다`() {
        // given
        val player = Player(
            UserInformation(Name("test"), BettingMoney(1000)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.CLUB, CardNumber.SEVEN)
                )
            )
        )
        val players = Players(listOf(player))
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.CLUB, CardNumber.SEVEN)
                )
            )
        )
        val playerGameResult = PlayersGameResult(mapOf(player to GameResultType.DRAW))

        // when
        val userBettingResult = UserBettingResult().getPlayersBettingResults(Users(players, dealer), playerGameResult)
        val profitMoney = userBettingResult[player]

        // then
        assertThat(profitMoney).isEqualTo(1000.0)
    }

    @Test
    fun `플레이어가 21을 초과할 경우 배팅 금액을 잃는다`() {
        // given
        val player = Player(
            UserInformation(Name("test"), BettingMoney(1000)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.CLUB, CardNumber.KING)
                )
            )
        )
        val players = Players(listOf(player))
        val dealer = Dealer()
        val playerGameResult = PlayersGameResult(mapOf(player to GameResultType.LOSE))

        // when
        val userBettingResult = UserBettingResult().getPlayersBettingResults(Users(players, dealer), playerGameResult)
        val profitMoney = userBettingResult[player]

        // then
        assertThat(profitMoney).isEqualTo(-1000.0)
    }

    @Test
    fun `딜러가 21을 초과할 경우 플레이어들이 배팅 금액을 받는다`() {
        // given
        val player = Player(
            UserInformation(Name("test"), BettingMoney(1000)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TWO),
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.CLUB, CardNumber.KING)
                )
            )
        )
        val players = Players(listOf(player))
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.CLUB, CardNumber.NINE)
                )
            )
        )
        val playerGameResult = PlayersGameResult(mapOf(player to GameResultType.WIN))

        // when
        val userBettingResult = UserBettingResult().getPlayersBettingResults(Users(players, dealer), playerGameResult)
        val profitMoney = userBettingResult[player]

        // then
        assertThat(profitMoney).isEqualTo(2000.0)
    }

    @Test
    fun `플레이어1의 최종 수익이 1000이고 플레이어2의 최종수익이 -2000이면 딜러의 최종 수익이 1000이다`() {
        // given
        val player1 = Player(UserInformation(Name("test"), BettingMoney(1000)), Cards(listOf()))
        val player2 = Player(UserInformation(Name("test"), BettingMoney(1000)), Cards(listOf()))
        val userBettingResult = UserBettingResult()
        userBettingResult.playersBettingResults[player1] = 1000.0
        userBettingResult.playersBettingResults[player2] = -2000.0

        // when
        val profitMoney = userBettingResult.getDealerBettingResult()

        // then
        assertThat(profitMoney).isEqualTo(1000.0)
    }
}
