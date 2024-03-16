package blackjack

import blackjack.model.BettingAmount
import blackjack.model.deck.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Judge
import blackjack.model.participant.Player
import blackjack.state.Bust
import blackjack.state.Stay
import blackjack.testmachine.BlackjackCardMachine
import blackjack.testmachine.BustCardMachine
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JudgeTest {
    private val judge = Judge()

    @Test
    fun `심판은 player가 블랙잭일 때 dealer가 블랙잭이 아니면 베팅 금액의 1,5배를 반환한다`() {
        // given
        val dealer = Dealer(Deck(NormalCardMachine()))
        val player = Player("채채", Deck(BlackjackCardMachine()))
        player.bettingAmount = BettingAmount(1000)
        // when
        val result = judge.calculateProfit(dealer, player)
        // then
        assertThat(result).isEqualTo(1500)
    }

    @Test
    fun `심판은 player가 블랙잭일 때 dealer도 블랙잭인 경우 비기므로 수익 0을 반환한다`() {
        // given
        val dealer = Dealer(Deck(BlackjackCardMachine()))
        val player = Player("채채", Deck(BlackjackCardMachine()))
        player.bettingAmount = BettingAmount(1000)
        // when
        val result = judge.calculateProfit(dealer, player)
        // then
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `심판은 player가 버스트일 때 베팅 금액의 -1배를 반환한다`() {
        // given
        val dealer = Dealer(Deck(NormalCardMachine()))
        val player = Player("채채", Deck(BustCardMachine()))
        player.bettingAmount = BettingAmount(1000)
        // when
        player.state = Bust(player).decisionState()
        val result = judge.calculateProfit(dealer, player)
        // then
        assertThat(result).isEqualTo(-1000)
    }

    @Test
    fun `심판은 player가 스테이이고 Dealer가 버스트일 때 베팅 금액의 1배를 반환한다`() {
        // given
        val dealer = Dealer(Deck(BustCardMachine()))
        dealer.addCard()
        val player = Player("채채", Deck(BustCardMachine()))
        player.bettingAmount = BettingAmount(1000)
        // when
        player.state = Stay(player).decisionState()
        val result = judge.calculateProfit(dealer, player)
        // then
        assertThat(result).isEqualTo(1000)
    }

    @Test
    fun `심판은 player, Dealer 모두 스테이일 때 총 합이 player가 더 크다면 베팅 금액의 1배를 반환한다`() {
        // given
        val dealer = Dealer(Deck(NormalCardMachine()))
        val player = Player("채채", Deck(NormalCardMachine()))
        player.addCard()
        player.bettingAmount = BettingAmount(1000)
        // when
        player.state = Stay(player).decisionState()
        val result = judge.calculateProfit(dealer, player)
        // then
        assertThat(result).isEqualTo(1000)
    }

    @Test
    fun `심판은 player, Dealer 모두 스테이일 때 총 합이 Dealer가 더 크다면 베팅 금액의 -1배를 반환한다`() {
        // given
        val dealer = Dealer(Deck(NormalCardMachine()))
        val player = Player("채채", Deck(NormalCardMachine()))
        dealer.addCard()
        player.bettingAmount = BettingAmount(1000)
        // when
        player.state = Stay(player).decisionState()
        val result = judge.calculateProfit(dealer, player)
        // then
        assertThat(result).isEqualTo(-1000)
    }

    @Test
    fun `심판은 player, Dealer 모두 스테이일 때 총 합이 같다면 베팅 금액의 0배를 반환한다`() {
        // given
        val dealer = Dealer(Deck(NormalCardMachine()))
        val player = Player("채채", Deck(NormalCardMachine()))
        player.bettingAmount = BettingAmount(1000)
        // when
        player.state = Stay(player).decisionState()
        val result = judge.calculateProfit(dealer, player)
        // then
        assertThat(result).isEqualTo(0)
    }
}
