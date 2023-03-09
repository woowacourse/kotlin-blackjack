package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `블랙잭 게임 세팅 - 이름이 해시, 배팅금액이 1000인 유저를 생성한다`() {
        // given
        val blackJackGame = BlackJackGame()
        val userNames = listOf<String>("해시")

        // when
        val blackJackGameData = blackJackGame.setUpBlackJackGame({ userNames }, { 1000 })

        // then
        assertThat(blackJackGameData.players.users[0].name).isEqualTo("해시")
        assertThat(blackJackGameData.players.users[0].betAmount).isEqualTo(1000.0)
    }

    @Test
    fun `유저 차례에 유저가 y, n를 입력해 카드를 한 장만 받는다`() {
        // given
        val blackJackGame = BlackJackGame()
        val userNames = listOf<String>("해시")

        // when
        val blackJackGameData = blackJackGame.setUpBlackJackGame({ userNames }, { 1000 })
        var index = 0
        blackJackGame.playUserTurn(blackJackGameData, { listOf(true, false)[index++] }, {})
        val userCards = blackJackGameData.players.users[0].cards.value

        // then
        assertThat(userCards.size).isEqualTo(3)
    }

    @Test
    fun `유저 차례에 유저가 n를 입력해 카드를 받지 않는다`() {
        // given
        val blackJackGame = BlackJackGame()
        val userNames = listOf<String>("해시")

        // when
        val blackJackGameData = blackJackGame.setUpBlackJackGame({ userNames }, { 1000 })
        blackJackGame.playUserTurn(blackJackGameData, { false }, {})
        val userCards = blackJackGameData.players.users[0].cards.value

        // then
        assertThat(userCards.size).isEqualTo(2)
    }

    @Test
    fun `딜러의 점수가 5이면 한 장 더 받아 카드가 3장이다`() {
        // given
        val blackJackGame = BlackJackGame()
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.TWO),
                Card(CardShape.DIAMONDS, CardValue.THREE)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            betAmount = 1000.0
        )
        val blackJackGameData = BlackJackGameData(Deck.create(1), players)

        // when
        blackJackGame.playDealerTurn(blackJackGameData) {}
        val actual = blackJackGameData.players.dealer.cards.value.size

        // then
        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `딜러의 점수가 20이면 한 장 더 받지 않아 카드가 2장이다`() {
        // given
        val blackJackGame = BlackJackGame()
        val players = createPlayersWithOneUser(
            dealerCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.TEN),
                Card(CardShape.DIAMONDS, CardValue.TEN)
            ),
            userCards = listOf(
                Card(CardShape.DIAMONDS, CardValue.ACE),
                Card(CardShape.DIAMONDS, CardValue.EIGHT)
            ),
            betAmount = 1000.0
        )
        val blackJackGameData = BlackJackGameData(Deck.create(1), players)

        // when
        blackJackGame.playDealerTurn(blackJackGameData) {}
        val actual = blackJackGameData.players.dealer.cards.value.size

        // then
        assertThat(actual).isEqualTo(2)
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
}
