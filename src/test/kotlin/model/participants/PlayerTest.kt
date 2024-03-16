package model.participants

import model.HEART_JACK
import model.HEART_QUEEN
import model.HEART_SEVEN
import model.HEART_TEN
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.makeDealer
import model.makePlayer
import model.makeTestDeck
import model.result.ResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var testDeck: Deck
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        testDeck =
            makeTestDeck(
                Card(ValueType.TWO, MarkType.SPADE),
                Card(ValueType.JACK, MarkType.SPADE),
                Card(ValueType.QUEEN, MarkType.SPADE),
                Card(ValueType.ACE, MarkType.SPADE),
            )
        player = makePlayer()
        dealer = makeDealer()
    }

    @Test
    fun `Player에서 게임 결과를 판단할 수 있다`() {
        dealer.hit(testDeck.pop())
        player.hit(testDeck.pop())

        val result = dealer.judge(player)
        assertThat(result).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `Player와 Dealer 모두 bust라면 Player의 패배`() {
        dealer.participantState = ParticipantState.Bust(Hand())
        player.participantState = ParticipantState.Bust(Hand())

        val result = player.judge(dealer)
        assertThat(result).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `Player와 Dealer 모두 BlakJack이면 Profit은 0`() {
        val player = Player(ParticipantState.BlackJack(Hand()), Wallet(ParticipantName.fromInput("Player"), money = Money(1000)))
        val dealer = Dealer(ParticipantState.BlackJack(Hand()))

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(0.0))
    }

    @Test
    fun `Player만 BlackJack이면 Profit은 150%`() {
        val player = Player(ParticipantState.BlackJack(Hand()), Wallet(ParticipantName.fromInput("Player"), money = Money(1000)))
        val dealer = Dealer(ParticipantState.Playing(Hand()))

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(1500.0))
    }

    @Test
    fun `Player가 Bust면 Profit은 -100%`() {
        val player = Player(ParticipantState.Bust(Hand()), Wallet(ParticipantName.fromInput("Player"), money = Money(1000)))
        val dealer = Dealer(ParticipantState.Playing(Hand()))

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(-1000.0))
    }

    @Test
    fun `Dealer만 Bust면 Profit은 100%`() {
        val player = Player(ParticipantState.Playing(Hand()), Wallet(ParticipantName.fromInput("Player"), money = Money(1000)))
        val dealer = Dealer(ParticipantState.Bust(Hand()))

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(1000.0))
    }

    @Test
    fun `Dealer 점수보다 Player 점수가 높으면 Profit은 100%`() {
        val playerHand = Hand()
        playerHand.draw(HEART_TEN)
        playerHand.draw(HEART_JACK)

        val dealerHand = Hand()
        dealerHand.draw(HEART_SEVEN)
        dealerHand.draw(HEART_QUEEN)

        val player = Player(ParticipantState.Playing(playerHand), Wallet(ParticipantName.fromInput("Player"), money = Money(1000)))
        val dealer = Dealer(ParticipantState.Playing(dealerHand))

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(1000.0))
    }

    @Test
    fun `Player 점수보다 Dealer 점수가 높으면 Profit은 -100%`() {
        val playerHand = Hand()
        playerHand.draw(HEART_SEVEN)
        playerHand.draw(HEART_QUEEN)

        val dealerHand = Hand()
        dealerHand.draw(HEART_TEN)
        dealerHand.draw(HEART_JACK)

        val player = Player(ParticipantState.Playing(playerHand), Wallet(ParticipantName.fromInput("Player"), money = Money(1000)))
        val dealer = Dealer(ParticipantState.Playing(dealerHand))

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(-1000.0))
    }
}
