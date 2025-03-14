package blackjack.domain.participant

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.participant.Dealer
import blackjack.model.participant.DrawManager
import blackjack.model.participant.Player
import blackjack.model.participant.UserCommand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DrawManagerTest {
    private lateinit var drawManager: DrawManager
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        drawManager = DrawManager()
        player = Player.create("공백")
        dealer = Dealer.create()
    }

    @Test
    fun `플레이어가 HIT을 선택하면 카드를 받는다`() {
        // given
        val draw: (Int) -> List<Card> = { listOf(Card(CardRank.TEN, CardSuit.HEART)) }
        val getCommand: () -> UserCommand = { UserCommand.HIT }
        val onCardReceived: (List<Card>) -> Unit = {}

        // when
        drawManager.progressPlayerDraw(player, draw, getCommand, onCardReceived)

        // then
        assertEquals(3, player.cards.size)
    }

    @Test
    fun `플레이어가 STAY를 선택하면 카드를 받지 않는다`() {
        // given
        val draw: (Int) -> List<Card> = { listOf(Card(CardRank.FIVE, CardSuit.HEART)) }
        val getCommand: () -> UserCommand = { UserCommand.STAY }
        val onCardReceived: (List<Card>) -> Unit = {}

        // when
        drawManager.progressPlayerDraw(player, draw, getCommand, onCardReceived)

        // then
        assertEquals(0, player.cards.size)
    }

    @Test
    fun `플레이어가 잘못된 명령을 입력하면 예외가 발생한다`() {
        // given
        val draw: (Int) -> List<Card> = { listOf(Card(CardRank.FIVE, CardSuit.HEART)) }
        val getCommand: () -> UserCommand = { UserCommand.UNKNOWN }
        val onCardReceived: (List<Card>) -> Unit = {}

        // when & then
        assertThrows<IllegalArgumentException> {
            drawManager.progressPlayerDraw(player, draw, getCommand, onCardReceived)
        }
    }

    @Test
    fun `딜러가 16 이하일 때 카드를 받는다`() {
        // given
        dealer.recieveCards { listOf(Card(CardRank.SIX, CardSuit.HEART)) }
        val draw: (Int) -> List<Card> = { listOf(Card(CardRank.FIVE, CardSuit.SPADE)) }

        // when
        drawManager.progressDealerDraw(dealer, draw)

        // then
        assertTrue(dealer.score > 16)
    }

    @Test
    fun `딜러가 17 이상이면 카드를 받지 않는다`() {
        // given
        dealer.recieveCards { listOf(Card(CardRank.TEN, CardSuit.HEART), Card(CardRank.SEVEN, CardSuit.SPADE)) }
        val draw: (Int) -> List<Card> = { listOf(Card(CardRank.FIVE, CardSuit.SPADE)) }

        // when
        drawManager.progressDealerDraw(dealer, draw)

        // then
        assertEquals(2, dealer.cards.size)
    }
}
