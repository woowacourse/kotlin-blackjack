package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitCalculatorTest {
    @Test
    fun `딜러가 블랙잭, 유저는 블랙잭이 아닌 경우 딜러의 수익은 1000, 유저의 수익은 -1000이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.TEN)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(1000.0)
        assertThat(userProfit).isEqualTo(-1000.0)
    }

    @Test
    fun `딜러가 블랙잭이 아니고, 유저는 블랙잭인 경우 딜러의 수익은 -1500, 유저의 수익은 1500이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.TEN)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(-1500.0)
        assertThat(userProfit).isEqualTo(1500.0)
    }

    @Test
    fun `딜러 유저 모두 블랙잭인 경우 딜러와 유저의 수익은 0이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(0.0)
        assertThat(userProfit).isEqualTo(0.0)
    }

    @Test
    fun `딜러 유저 모두 블랙잭 아니고, 딜러가 이긴 경우 딜러의 수익은 1000, 유저의 수익은 -1000이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.THREE)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(1000.0)
        assertThat(userProfit).isEqualTo(-1000.0)
    }

    @Test
    fun `딜러 유저 모두 블랙잭 아니고, 유저가 이긴 경우 딜러의 수익은 -1000, 유저의 수익은 1000이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.NINE)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(-1000.0)
        assertThat(userProfit).isEqualTo(1000.0)
    }

    @Test
    fun `딜러는 버스트, 유저는 버스트가 아닌 경우 딜러의 수익은 -1000, 유저의 수익은 1000이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT),
                Card(CardShape.DIAMONDS, CardValue.NINE)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.NINE)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(-1000.0)
        assertThat(userProfit).isEqualTo(1000.0)
    }

    @Test
    fun `딜러는 버스트, 유저는 블랙잭인 경우 딜러의 수익은 -1500, 유저의 수익은 1500이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.SEVEN),
                Card(CardShape.DIAMONDS, CardValue.EIGHT),
                Card(CardShape.DIAMONDS, CardValue.NINE)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.TEN)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(-1500.0)
        assertThat(userProfit).isEqualTo(1500.0)
    }

    @Test
    fun `딜러는 유저 모두 버스트인 경우 딜러의 수익은 0, 유저의 수익은 0이다`() {
        // given
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.SEVEN),
                Card(CardShape.DIAMONDS, CardValue.EIGHT),
                Card(CardShape.DIAMONDS, CardValue.NINE)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.SEVEN),
                Card(CardShape.DIAMONDS, CardValue.NINE),
                Card(CardShape.DIAMONDS, CardValue.NINE),
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val userProfit = profitCalculator.getUsersProfit()[0].profit

        // then
        assertThat(dealerProfit).isEqualTo(0.0)
        assertThat(userProfit).isEqualTo(0.0)
    }

    @Test
    fun `딜러 블랙잭 아니고, 유저1은 블랙잭, 유저2는 진 경우 딜러의 수익은 -500, 유저1 수익은 1500, 유저2 수익은 -1000이다`() {
        // given
        val players = createPlayersWithTwoUsers(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            user1Cards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.TEN)
            ),
            user2Cards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.TWO)
            ),
            betAmount = 1000.0
        )
        val referee = Referee(players.dealerScore)
        referee.getResult(players.users)

        val profitCalculator = ProfitCalculator(players)

        // when
        val dealerProfit = profitCalculator.getDealerProfit()
        val user1Profit = profitCalculator.getUsersProfit()[0].profit
        val user2Profit = profitCalculator.getUsersProfit()[1].profit

        // then
        assertThat(dealerProfit).isEqualTo(-500.0)
        assertThat(user1Profit).isEqualTo(1500.0)
        assertThat(user2Profit).isEqualTo(-1000.0)
    }

    private fun createPlayersWithOneUser(
        dealerCards: List<Card>,
        userCards: List<Card>,
        betAmount: Double
    ): Players {
        val dealer = Dealer(cards = Cards(dealerCards))
        val users = listOf<User>(
            User(
                "해시",
                cards = Cards(userCards),
                betAmount = betAmount
            )
        )
        return Players(dealer, users)
    }

    private fun createPlayersWithTwoUsers(
        dealerCards: List<Card>,
        user1Cards: List<Card>,
        user2Cards: List<Card>,
        betAmount: Double
    ): Players {
        val dealer = Dealer(cards = Cards(dealerCards))
        val users = listOf<User>(
            User(
                "해시",
                cards = Cards(user1Cards),
                betAmount = betAmount
            ),
            User(
                "해시",
                cards = Cards(user2Cards),
                betAmount = betAmount
            )
        )
        return Players(dealer, users)
    }
}
