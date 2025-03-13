package blackjack.domain.service

import blackjack.domain.model.BetAmount
import blackjack.domain.model.Proceed
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardFactory.Companion.cardNumbers
import blackjack.domain.model.card.CardFactory.Companion.symbols
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.card.Shape
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
    fun `게임이 끝난 후 수익 금액을 계산한다`() {
        // given
        player1.receiveCard(listOf(Card(Shape.Spade, CardNumber.Ace))) // 11
        player2.receiveCard(listOf(Card(Shape.Spade, CardNumber.Six))) // 6
        player3.receiveCard(listOf(Card(Shape.Heart, CardNumber.Seven))) // 7
        dealer.receiveCard(listOf(Card(Shape.Spade, CardNumber.Seven))) // 7

        val betAmount: Map<Player, BetAmount> =
            mapOf(
                player1 to BetAmount(1000),
                player2 to BetAmount(2000),
                player3 to BetAmount(3000),
            )
        // when
        val gameResult = game.getGameResult(betAmount)
        // then
        assertThat(gameResult[player1]).isEqualTo(Proceed(1000))
        assertThat(gameResult[player2]).isEqualTo(Proceed(-2000))
        assertThat(gameResult[player3]).isEqualTo(Proceed(0))
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
    fun `플레이어가 hit을 외치면 카드 한장을 뽑습니다`() {
        // when
        val size = player1.cardDeck.size
        game.hitAction(player1)
        // then
        assertThat(player1.cardDeck.size).isEqualTo(size + 1)
    }
}
