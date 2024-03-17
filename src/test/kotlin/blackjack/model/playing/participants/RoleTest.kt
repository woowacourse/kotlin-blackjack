package blackjack.model.playing.participants

import blackjack.model.Betting
import blackjack.model.CLOVER_ACE
import blackjack.model.DIAMOND_KING
import blackjack.model.HEART_ACE
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_NINE
import blackjack.model.HEART_SEVEN
import blackjack.model.HEART_TEN
import blackjack.model.SPADE_SIX
import blackjack.model.card.RandomDeck
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.winning.WinningResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoleTest {
    @Test
    fun `처음 카드를 2장 받는다`() {
        val dealer = Dealer(CardHand(emptyList()))
        val player = Player(PlayerName("해나"), CardHand(emptyList()), Betting(0))
        val deck = RandomDeck()

        dealer.addInitialCards(deck)
        player.addInitialCards(deck)

        assertThat(dealer.cardHand.hand.size).isEqualTo(2)
        assertThat(player.cardHand.hand.size).isEqualTo(2)
    }

    @Test
    fun `딜러가 Bust일 때 플레이어는 Bust가 아니면 딜러는 패배한다`() {
        val dealer =
            Dealer(
                CardHand(
                    HEART_TEN,
                    HEART_NINE,
                    SPADE_SIX,
                ),
            )
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    HEART_TEN,
                    HEART_NINE,
                ),
                Betting(0),
            )
        assertThat(dealer.match(player)).isEqualTo(WinningResultStatus.DEFEAT)
    }

    @Test
    fun `딜러와 플레이어 모두 Bust이면 딜러가 승리한다`() {
        val dealer =
            Dealer(
                CardHand(
                    HEART_TEN,
                    HEART_NINE,
                    SPADE_SIX,
                ),
            )
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    HEART_TEN,
                    HEART_NINE,
                    HEART_FIVE,
                ),
                Betting(0),
            )
        assertThat(dealer.match(player)).isEqualTo(WinningResultStatus.VICTORY)
    }

    @Test
    fun `딜러와 플레이어의 점수가 동일하면 무승부이다`() {
        val dealer =
            Dealer(
                CardHand(
                    HEART_TEN,
                    SPADE_SIX,
                ),
            )
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    HEART_TEN,
                    SPADE_SIX,
                ),
                Betting(0),
            )
        assertThat(dealer.match(player)).isEqualTo(WinningResultStatus.PUSH)
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭이면 무승부이다`() {
        val dealer =
            Dealer(
                CardHand(
                    HEART_TEN,
                    CLOVER_ACE,
                ),
            )
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    DIAMOND_KING,
                    HEART_ACE,
                ),
                Betting(0),
            )
        assertThat(dealer.match(player)).isEqualTo(WinningResultStatus.PUSH)
    }

    @Test
    fun `딜러, 플레이어 모두 Bust가 아니고, 딜러의 점수가 플레이어의 점수보다 낮으면 딜러 패`() {
        val dealer =
            Dealer(
                CardHand(
                    HEART_TEN,
                    SPADE_SIX,
                ),
            )
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    HEART_TEN,
                    DIAMOND_KING,
                ),
                Betting(0),
            )
        assertThat(dealer.match(player)).isEqualTo(WinningResultStatus.DEFEAT)
    }

    @Test
    fun `딜러, 플레이어 모두 Bust가 아니고, 딜러의 점수가 플레이어의 점수보다 높으면 딜러 승`() {
        val dealer =
            Dealer(
                CardHand(
                    HEART_TEN,
                    HEART_SEVEN,
                ),
            )
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    HEART_TEN,
                    HEART_FIVE,
                ),
                Betting(0),
            )
        assertThat(dealer.match(player)).isEqualTo(WinningResultStatus.VICTORY)
    }

    @Test
    fun `플레이어가 블랙잭이면 베팅 금액의 1,5 배를 딜러에게 받는다`() {
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    DIAMOND_KING,
                    HEART_ACE,
                ),
                Betting(10000),
            )
        val actual = player.calculateProfit(WinningResultStatus.VICTORY)
        assertThat(actual).isEqualTo(15000.0)
    }

    @Test
    fun `플레이어가 이기면(블랙잭 아닐 때) 베팅 금액을 딜러에게 받는다`() {
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    DIAMOND_KING,
                    SPADE_SIX,
                ),
                Betting(10000),
            )
        val actual = player.calculateProfit(WinningResultStatus.VICTORY)
        assertThat(actual).isEqualTo(10000.0)
    }

    @Test
    fun `플레이어와 딜러가 무승부이면 플레이어는 베팅 금액을 돌려 받는다`() {
        val player =
            Player(
                PlayerName("해나"),
                CardHand(emptyList()),
                Betting(10000),
            )
        val actual = player.calculateProfit(WinningResultStatus.PUSH)
        assertThat(actual).isEqualTo(0.0)
    }

    @Test
    fun `플레이어가 지면 베팅 금액을 잃는다`() {
        val player =
            Player(
                PlayerName("해나"),
                CardHand(emptyList()),
                Betting(10000),
            )
        val actual = player.calculateProfit(WinningResultStatus.DEFEAT)
        assertThat(actual).isEqualTo(-10000.0)
    }
}
