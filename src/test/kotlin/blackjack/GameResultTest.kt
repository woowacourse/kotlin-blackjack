package blackjack

import blackjack.model.BettingAmount
import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.GameResult
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.ParticipantInformation.PlayerInformation
import blackjack.model.ParticipantName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var players: List<Player>
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        players = listOf(Player(PlayerInformation(ParticipantName("채드"), BettingAmount(1000.0))))
        dealer = Dealer()
        dealer.draw(Card(CardNumber.TEN, CardSymbol.SPADE))
        dealer.draw(Card(CardNumber.NINE, CardSymbol.HEART))
    }

    @Test
    fun `딜러 승, 플레이어 패일때 수익 계산`() {
        players.forEach { player ->
            player.draw(Card(CardNumber.JACK, CardSymbol.SPADE))
            player.draw(Card(CardNumber.EIGHT, CardSymbol.SPADE))
        }

        val dealerRevenue = GameResult(dealer, players).calculateDealerRevenue().amount
        val playersRevenue = GameResult(dealer, players).playersRevenue

        assertThat(dealerRevenue).isEqualTo(1000.0)
        assertThat(playersRevenue.all { playerRevenue -> playerRevenue.amount == -1000.0 }).isTrue
    }

    @Test
    fun `딜러 패, 플레이어 승일때 수익 계산`() {
        players.forEach { player ->
            player.draw(Card(CardNumber.JACK, CardSymbol.SPADE))
            player.draw(Card(CardNumber.KING, CardSymbol.SPADE))
        }

        val dealerRevenue = GameResult(dealer, players).calculateDealerRevenue().amount
        val playersRevenue = GameResult(dealer, players).playersRevenue

        assertThat(dealerRevenue).isEqualTo(-1000.0)
        assertThat(playersRevenue.all { playerRevenue -> playerRevenue.amount == 1000.0 }).isTrue
    }

    @Test
    fun `딜러 패, 플레이어 승(블랙잭)일때 수익 계산`() {
        players.forEach { player ->
            player.draw(Card(CardNumber.JACK, CardSymbol.SPADE))
            player.draw(Card(CardNumber.ACE, CardSymbol.SPADE))
        }

        val dealerRevenue = GameResult(dealer, players).calculateDealerRevenue().amount
        val playersRevenue = GameResult(dealer, players).playersRevenue

        assertThat(dealerRevenue).isEqualTo(-1500.0)
        assertThat(playersRevenue.all { playerRevenue -> playerRevenue.amount == 1500.0 }).isTrue
    }

    @Test
    fun `딜러 플레이어 무승부일떄 수익 계산`() {
        players.forEach { player ->
            player.draw(Card(CardNumber.JACK, CardSymbol.SPADE))
            player.draw(Card(CardNumber.NINE, CardSymbol.SPADE))
        }

        val dealerRevenue = GameResult(dealer, players).calculateDealerRevenue().amount
        val playersRevenue = GameResult(dealer, players).playersRevenue

        assertThat(dealerRevenue).isEqualTo(0.0)
        assertThat(playersRevenue.all { playerRevenue -> playerRevenue.amount == 0.0 }).isTrue
    }
}
