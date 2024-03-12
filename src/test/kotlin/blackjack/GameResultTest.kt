package blackjack

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.ParticipantName
import blackjack.model.Participants
import blackjack.model.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var participants: Participants

    @BeforeEach
    fun setUp() {
        val dealer = Dealer()
        val players = listOf(Player(ParticipantName("채드")))
        val participantsList = mutableListOf<Participant>()
        participantsList.add(dealer)
        participantsList.addAll(players)
        participants = Participants(participantsList)

        dealer.draw(Card(CardNumber.TEN, CardSymbol.SPADE))
        dealer.draw(Card(CardNumber.TEN, CardSymbol.HEART))
    }

    @Test
    fun `딜러 승, 플레이어 패`() {
        participants.getPlayers().forEach { player ->
            player.draw(Card(CardNumber.JACK, CardSymbol.SPADE))
            player.draw(Card(CardNumber.NINE, CardSymbol.SPADE))
        }

        val gameResult = GameResult(participants)

        assertThat(gameResult.dealerResult[Result.DEALER_WIN]).isEqualTo(1)
        assertThat(gameResult.playerResults.all { playerResult -> playerResult == Result.DEALER_WIN })
    }

    @Test
    fun `딜러 패, 플레이어 승`() {
        participants.getPlayers().forEach { player ->
            player.draw(Card(CardNumber.JACK, CardSymbol.SPADE))
            player.draw(Card(CardNumber.ACE, CardSymbol.SPADE))
        }

        val gameResult = GameResult(participants)

        assertThat(gameResult.dealerResult[Result.PLAYER_WIN]).isEqualTo(1)
        assertThat(gameResult.playerResults.all { playerResult -> playerResult == Result.PLAYER_WIN })
    }

    @Test
    fun `딜러, 플레이어 무승부`() {
        participants.getPlayers().forEach { player ->
            player.draw(Card(CardNumber.JACK, CardSymbol.SPADE))
            player.draw(Card(CardNumber.QUEEN, CardSymbol.SPADE))
        }

        val gameResult = GameResult(participants)

        assertThat(gameResult.dealerResult[Result.TIE]).isEqualTo(1)
        assertThat(gameResult.playerResults.all { playerResult -> playerResult == Result.TIE })
    }
}
