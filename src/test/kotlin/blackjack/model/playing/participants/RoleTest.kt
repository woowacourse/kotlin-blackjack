package blackjack.model.playing.participants

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
        val player = Player(PlayerName("해나"), CardHand(emptyList()))
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
            )
        assertThat(dealer.match(player)).isEqualTo(WinningResultStatus.VICTORY)
    }
}
