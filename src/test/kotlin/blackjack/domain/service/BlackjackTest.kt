package blackjack.domain.service

import blackjack.domain.SPADE_SEVEN
import blackjack.domain.blackjackCardList
import blackjack.domain.bustCardList
import blackjack.domain.model.BetAmount
import blackjack.domain.model.BetStatus
import blackjack.domain.model.Proceed
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardFactory.Companion.cardNumbers
import blackjack.domain.model.card.CardFactory.Companion.symbols
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.PlayerGroup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackTest {
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var player3: Player
    private lateinit var players: List<Player>
    private lateinit var dealer: Dealer
    private lateinit var game: Blackjack

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
        game = Blackjack(deck, PlayerGroup(listOf(player1, player2, player3, dealer)))
    }

    @Test
    fun `게임 시작시 카드를 2장을 나눈다`() {
        // when
        game.initGame()
        // then
        assertThat(dealer.cardDeck.size).isEqualTo(2)
        assertThat(player1.cardDeck.size).isEqualTo(2)
        assertThat(player2.cardDeck.size).isEqualTo(2)
    }

    @Test
    fun `플레이어가 hit을 외치면 카드 한장을 뽑는다`() {
        // when
        val size = player1.cardDeck.size
        game.hitAction(player1)
        // then
        assertThat(player1.cardDeck.size).isEqualTo(size + 1)
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 카드를 추가로 받는다`() {
        // given
        game.initGame()
        // when
        game.drawUntilDealerStands()
        // then
        assertThat(dealer.cardDeck.size).isGreaterThan(2)
    }

    @Test
    fun `딜러와 플레이어들의 수익 금액을 반환한다`() {
        // given
        player1.receiveCard(blackjackCardList())
        player2.receiveCard(bustCardList())
        player3.receiveCard(listOf(SPADE_SEVEN))
        dealer.receiveCard(listOf(SPADE_SEVEN))

        val betStatus = players.map { BetStatus(it, BetAmount(10000)) }
        // when
        val gameResult = game.endGame(betStatus)
        // then
        assertThat(gameResult.find { it.participant == player1 }!!.proceed).isEqualTo(Proceed(15000))
        assertThat(gameResult.find { it.participant == player2 }!!.proceed).isEqualTo(Proceed(-10000))
        assertThat(gameResult.find { it.participant == player3 }!!.proceed).isEqualTo(Proceed(0))
        assertThat(gameResult.find { it.participant == dealer }!!.proceed).isEqualTo(Proceed(-5000))
    }
}
