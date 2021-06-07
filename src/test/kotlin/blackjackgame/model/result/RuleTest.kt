package blackjackgame.model.result

import blackjackgame.model.card.Card
import blackjackgame.model.card.Denomination
import blackjackgame.model.card.Suit
import blackjackgame.model.player.Dealer
import blackjackgame.model.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Nested
@DisplayName("딜러가 21을 넘지 않는 경우는")
internal class DealerNotOver21 {

    @DisplayName("21을 넘지 않으면서 21에 가까운 플레이어가 우승한다.")
    @Test
    fun playerWin() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Suit.DIAMOND, Denomination.TWO), Card(Suit.SPADE, Denomination.TWO)))
        val player = Player()
        player.drawCard(listOf(Card(Suit.DIAMOND, Denomination.THREE), Card(Suit.SPADE, Denomination.THREE)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(WIN)
    }

    @DisplayName("딜러와 점수가 동일하면 비긴다.")
    @Test
    fun playerDraw() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Suit.DIAMOND, Denomination.TWO), Card(Suit.SPADE, Denomination.TWO)))
        val player = Player()
        player.drawCard(listOf(Card(Suit.DIAMOND, Denomination.TWO), Card(Suit.SPADE, Denomination.TWO)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(DRAW)
    }

    @DisplayName("딜러보다 점수가 낮으면 진다.")
    @Test
    fun playerLose() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Suit.DIAMOND, Denomination.THREE), Card(Suit.SPADE, Denomination.THREE)))
        val player = Player()
        player.drawCard(listOf(Card(Suit.DIAMOND, Denomination.TWO), Card(Suit.SPADE, Denomination.TWO)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(LOSE)
    }

    @DisplayName("플레이어가 21점을 넘으면 진다.")
    @Test
    fun playerIsBurst() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Suit.DIAMOND, Denomination.THREE), Card(Suit.SPADE, Denomination.THREE)))
        val player = Player()
        player.drawCard(listOf(Card(Suit.HEART, Denomination.KING), Card(Suit.SPADE, Denomination.QUEEN),
            Card(Suit.CLOVER, Denomination.TWO)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(LOSE)
    }

}

@Nested
@DisplayName("딜러가 블랙잭인 경우는")
internal class DealerIsBlackjack {

    @Test
    @DisplayName("플레이어는 21이 넘지 않으면서 블랙잭이 아니면 진다.")
    fun playerIsNotBlackjack() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Suit.DIAMOND, Denomination.ACE), Card(Suit.SPADE, Denomination.KING)))
        val player = Player()
        player.drawCard(listOf(Card(Suit.DIAMOND, Denomination.TWO), Card(Suit.SPADE, Denomination.TWO)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(LOSE)
    }

    @Test
    @DisplayName("플레이어도 블랙잭이면 비긴다.")
    fun playerIsBlackjack() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Suit.DIAMOND, Denomination.ACE), Card(Suit.SPADE, Denomination.KING)))
        val player = Player()
        player.drawCard(listOf(Card(Suit.HEART, Denomination.ACE), Card(Suit.SPADE, Denomination.QUEEN)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(DRAW)
    }

    @Test
    @DisplayName("플레이어가 21점 초과이면 진다.")
    fun playerIsBurst() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Suit.DIAMOND, Denomination.ACE), Card(Suit.SPADE, Denomination.KING)))
        val player = Player()
        player.drawCard(listOf(Card(Suit.HEART, Denomination.ACE), Card(Suit.SPADE, Denomination.QUEEN),
            Card(Suit.CLOVER, Denomination.TWO)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(LOSE)
    }
}

@Nested
@DisplayName("딜러가 21을 넘는 경우는")
class DealerOver21{
    @Test
    @DisplayName("플레이어는 21 미만인 경우 이긴다.")
     fun playerIsHit() {
        val dealer = Dealer()
        dealer.drawCard(listOf(
            Card(Suit.DIAMOND, Denomination.QUEEN),
            Card(Suit.SPADE, Denomination.KING),
            Card(Suit.DIAMOND, Denomination.TWO)
        ))
        val player = Player()
        player.drawCard(listOf(Card(Suit.SPADE, Denomination.QUEEN), Card(Suit.CLOVER, Denomination.TWO)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(WIN)
    }

    @Test
    @DisplayName("플레이어는 21인 경우 이긴다.")
    fun playerIsBlackjack() {
        val dealer = Dealer()
        dealer.drawCard(listOf(
            Card(Suit.DIAMOND, Denomination.QUEEN),
            Card(Suit.SPADE, Denomination.KING),
            Card(Suit.DIAMOND, Denomination.TWO)
        ))
        val player = Player()
        player.drawCard(listOf(Card(Suit.SPADE, Denomination.QUEEN), Card(Suit.CLOVER, Denomination.ACE)))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(WIN)
    }

    @Test
    @DisplayName("플레이어는 21인 초과이어도 이긴다.")
    fun playerIsBurst() {
        val dealer = Dealer()
        dealer.drawCard(listOf(
            Card(Suit.DIAMOND, Denomination.QUEEN),
            Card(Suit.SPADE, Denomination.KING),
            Card(Suit.DIAMOND, Denomination.TWO)
        ))
        val player = Player()
        player.drawCard(listOf(
            Card(Suit.SPADE, Denomination.QUEEN),
            Card(Suit.CLOVER, Denomination.KING),
            Card(Suit.CLOVER, Denomination.TWO)
        ))

        assertThat(getPlayerResult(dealer, player)).isEqualTo(WIN)
    }
}