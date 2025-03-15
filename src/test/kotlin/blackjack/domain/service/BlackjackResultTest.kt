package blackjack.domain.service

import blackjack.domain.SPADE_SEVEN
import blackjack.domain.blackjackCardList
import blackjack.domain.bustCardList
import blackjack.domain.model.BetAmount
import blackjack.domain.model.BetStatus
import blackjack.domain.model.Proceed
import blackjack.domain.model.ProceedStatus
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardFactory.Companion.cardNumbers
import blackjack.domain.model.card.CardFactory.Companion.symbols
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackResultTest {
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var player3: Player
    private lateinit var players: List<Player>
    private lateinit var dealer: Dealer
    private lateinit var gameResult: BlackjackResult

    // given
    @BeforeEach
    fun setup() {
        player1 = Player("제리")
        player2 = Player("환노")
        player3 = Player("포르")
        players = listOf(player1, player2, player3)
        dealer = Dealer()
        val card = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }.toMutableList()
        val deck = PlayingCard(ArrayDeque(card))
        gameResult = BlackjackResult(dealer)
    }

    @Test
    fun `게임이 끝난 후 플레이어의 수익 금액을 계산한다`() {
        // given
        player1.receiveCard(blackjackCardList())
        player2.receiveCard(bustCardList())
        player3.receiveCard(listOf(SPADE_SEVEN))
        dealer.receiveCard(listOf(SPADE_SEVEN))

        val betStatus = players.map { BetStatus(it, BetAmount(10000)) }

        // when
        val gameResult = gameResult.calculatePlayersProceed(betStatus)
        // then
        assertThat(gameResult.find { it.participant == player1 }!!.proceed).isEqualTo(Proceed(15000))
        assertThat(gameResult.find { it.participant == player2 }!!.proceed).isEqualTo(Proceed(-10000))
        assertThat(gameResult.find { it.participant == player3 }!!.proceed).isEqualTo(Proceed(0))
    }

    @Test
    fun `딜러의 수익 금액은 플레이어의 총 수익 금액과 부호가 반대다`() {
        // given
        val player1Proceed = ProceedStatus(player1, Proceed(1000))
        val player2Proceed = ProceedStatus(player2, Proceed(2000))
        val player3Proceed = ProceedStatus(player3, Proceed(-2000))

        val playerProceedStatus = listOf(player1Proceed, player2Proceed, player3Proceed)
        // when
        val dealerResult = gameResult.calculateDealerProceed(playerProceedStatus)
        // then
        assertThat(dealerResult.proceed).isEqualTo(Proceed(-1000))
    }
}
