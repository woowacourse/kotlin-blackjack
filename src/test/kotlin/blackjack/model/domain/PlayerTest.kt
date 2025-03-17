package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    private val player1 = Player("제리")

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        // given
        player1.receiveCard(listOf(Card.from("AceHeart"), Card.from("SixSpade")))
        // when
        val actual = player1.cardDeck
        val expected = listOf(Card.from("AceHeart"), Card.from("SixSpade"))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 숫자의 합을 토대로 bust를 판단한다`() {
        // given
        player1.receiveCard(listOf(Card.from("QueenHeart"), Card.from("QueenSpade"), Card.from("QueenClover")))
        // when
        val actual = player1.hand.isBust()
        val expected = true
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `상대 상태가 BlackJack 이고 내 상태도 BlackJack이면 게임이 비긴다`() {
        // given
        val blackjackDealer = Dealer()
        blackjackDealer.receiveCard(listOf(Card.from("QueenHeart"), Card.from("AceSpade")))
        player1.receiveCard(listOf(Card.from("QueenHeart"), Card.from("AceHeart")))
        // when
        val actual = player1.compareScores(blackjackDealer.hand)
        val expected = GameResult.Draw
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `상대 상태가 BlackJack 이면 게임이 진다`() {
        // given
        val blackjackDealer = Dealer()
        blackjackDealer.receiveCard(listOf(Card.from("QueenHeart"), Card.from("AceSpade")))
        player1.receiveCard(listOf(Card.from("QueenHeart"), Card.from("QueenClover")))
        // when
        val actual = player1.compareScores(blackjackDealer.hand)
        val expected = GameResult.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `내 상태가 BlackJack 이고 상대 상태가 BlackJack이 아니면 게임이 Blackjack 상태로 이긴다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(listOf(Card.from("NineClover"), Card.from("TenSpade")))
        player1.receiveCard(listOf(Card.from("QueenHeart"), Card.from("AceHeart")))
        // when
        val actual = player1.compareScores(dealer.hand)
        val expected = GameResult.BlackjackWin
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `내 상태가 BlackJack 이고 상대 상태가 Bust면 게임이 Blackjack 상태로 이긴다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(listOf(Card.from("NineClover"), Card.from("TenSpade"), Card.from("QueenHeart")))
        player1.receiveCard(listOf(Card.from("QueenHeart"), Card.from("AceHeart")))
        // when
        val actual = player1.compareScores(dealer.hand)
        val expected = GameResult.BlackjackWin
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `내 상태가 Bust 이고 상대 상태가 Bust면 게임이 진다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(listOf(Card.from("NineClover"), Card.from("TenSpade"), Card.from("QueenHeart")))
        player1.receiveCard(listOf(Card.from("NineHeart"), Card.from("TenClover"), Card.from("QueenSpade")))
        // when
        val actual = player1.compareScores(dealer.hand)
        val expected = GameResult.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 숫자의 합과 받은 숫자의 합을 비교하여 승패를 결정한다`() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(listOf(Card.from("NineClover"), Card.from("TenSpade")))
        player1.receiveCard(listOf(Card.from("QueenHeart"), Card.from("QueenHeart")))
        // when
        val actual = player1.compareScores(dealer.hand)
        val expected = GameResult.Win
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
