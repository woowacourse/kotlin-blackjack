package blackjack.model.service

import blackjack.model.domain.ActionType
import blackjack.model.domain.CardFactory.Companion.cardNumbers
import blackjack.model.domain.CardFactory.Companion.symbols
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardNumber
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.card.Shape
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.ParticipantStatus
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerGroup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackTest {
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var player3: Player
    private lateinit var dealer: Dealer
    private lateinit var card: List<Card>
    private lateinit var deck: PlayingCard
    private lateinit var game: Blackjack

    // given
    @BeforeEach
    fun setup() {
        player1 = Player("제리")
        player2 = Player("환노")
        player3 = Player("포르")
        dealer = Dealer()
        card = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }.toMutableList()
        deck = PlayingCard(ArrayDeque(card))
        game = Blackjack(deck)
    }

    @Test
    fun `게임 시작시 카드를 2장을 나눈다`() {
        // when
        game.initGame(listOf(player1, player2, dealer))
        // then
        assertThat(dealer.cardDeck.size).isEqualTo(2)
        assertThat(player1.cardDeck.size).isEqualTo(2)
        assertThat(player2.cardDeck.size).isEqualTo(2)
    }

    @Test
    fun `게임이 끝난 후 승패를 가린다`() {
        // when
        player1.receiveCard(Card(Shape.Spade, CardNumber.Ace))
        player2.receiveCard(Card(Shape.Spade, CardNumber.Six))
        player3.receiveCard(Card(Shape.Heart, CardNumber.Seven))
        dealer.receiveCard(Card(Shape.Spade, CardNumber.Seven))
        game.endGame(PlayerGroup(listOf(player1, player2, player3), dealer))
        // then
        assertThat(player1.status).isEqualTo(ParticipantStatus.Win)
        assertThat(player2.status).isEqualTo(ParticipantStatus.Lose)
        assertThat(player3.status).isEqualTo(ParticipantStatus.Draw)
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 카드를 추가로 받는다`() {
        // when
        game.initGame(listOf(player1, player2, dealer))
        game.drawUntilThreshold(dealer)
        // then
        assertThat(dealer.cardDeck.size).isGreaterThan(2)
    }

    @Test
    fun `플레이어는 stay를 외칠 시 카드를 그만 받는다`() {
        // when
        val initPlayerCardSize = player1.cardDeck.size
        game.shouldStopDrawing(ActionType.Stay, player1)
        // then
        assertThat(player1.cardDeck.size).isEqualTo(initPlayerCardSize)
    }

    @Test
    fun `플레이어는 hit을 외칠 시 카드를 한장 받는다`() {
        // when
        val initPlayerCardSize = player1.cardDeck.size
        game.shouldStopDrawing(ActionType.Hit, player1)
        // then
        assertThat(player1.cardDeck.size).isEqualTo(initPlayerCardSize + 1)
    }
}
