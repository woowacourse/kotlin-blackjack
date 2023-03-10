package domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Shape
import blackjack.domain.dealer.Dealer
import blackjack.domain.gameResult.PlayerGameResult
import blackjack.domain.gameResult.PlayerGameResults
import blackjack.domain.gameResult.ProfitMoney
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.BattingMoney
import blackjack.domain.player.BlackJackPlayers
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackPlayersTest {

    @Test
    fun `모든 플레이어가 첫 두장을 받은 이후에 추가적인 카드를 원한다면 최소합이 21이상이 되기 전까지만 받을 수 있다`() {
        val blackJackPlayers = BlackJackPlayers(
            listOf(
                Player("woogi", 1000),
                Player("james", 1000),
                Player("ring", 1000),
                Player("scott", 1000),
                Player("sunny", 1000))
        )

        blackJackPlayers.drawAdditionalCardsForPlayers(
            isPlayerWantedAdditionalCards = { true }
        )

        assertThat(
            blackJackPlayers.players.all { player -> player.cards.getMinimumCardsScore() >= 21 }
        ).isTrue
    }

    @Test
    fun `배팅 금액이 모두 1000원이고, 딜러를 상대로 우기는 승리하고(블랙잭 승리x) 링링은 패배 써니는 무승부일때 링링은 -1000원 우기는 1000원 써니는 0원의 이득 결과를 얻게 된다`() {
        val woogi = Player(
            name = PlayerName("woogi"),
            battingMoney = BattingMoney(1000),
            cards = Cards(
                listOf(
                    Card(CardNumber.SEVEN, Shape.SPADE),
                    Card(CardNumber.K, Shape.SPADE)
                )
            )
        )

        val ring = Player(
            name = PlayerName("ring"),
            battingMoney = BattingMoney(1000),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.ONE, Shape.DIAMOND)
                )
            )
        )

        val sunny = Player(
            name = PlayerName("sunny"),
            battingMoney = BattingMoney(1000),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.TWO, Shape.DIAMOND)
                )
            )
        )

        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardNumber.SIX, Shape.DIAMOND),
                    Card(CardNumber.FOUR, Shape.CLOVER)
                )
            )
        )

        val blackJackPlayers = BlackJackPlayers(listOf(woogi, ring, sunny))

        val actual = blackJackPlayers.judgePlayerGameResults(dealer.cards.state)

        assertThat(actual).isEqualTo(
            TotalGameResult(
                playerGameResults = PlayerGameResults(
                    listOf(
                        PlayerGameResult("woogi", ProfitMoney(1000)),
                        PlayerGameResult("ring", ProfitMoney(-1000)),
                        PlayerGameResult("sunny", ProfitMoney(0))
                    )
                ),
                dealerGameResults = ProfitMoney(0)
            )
        )
    }
}