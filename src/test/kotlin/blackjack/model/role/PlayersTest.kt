package blackjack.model.role

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import blackjack.model.card.state.BlackJack
import blackjack.model.card.state.Bust
import blackjack.model.card.state.Stay
import blackjack.model.result.PlayerWinning
import blackjack.model.result.WinningResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `플레이어들 이름에 중복이 없고 딜러를 제외한 수가 1명 이상 6명 이하여야 한다`() {
        assertDoesNotThrow {
            Players(
                listOf(
                    Player(PlayerName("심지")),
                    Player(PlayerName("해나")),
                    Player(PlayerName("팡태")),
                    Player(PlayerName("악어")),
                    Player(PlayerName("올리브")),
                    Player(PlayerName("서기")),
                ),
            )
        }
    }

    @Test
    fun `플레이어들의 이름에 중복이 있으면 안된다`() {
        assertThrows<IllegalArgumentException> {
            Players(
                listOf(
                    Player(PlayerName("심지")),
                    Player(PlayerName("해나")),
                    Player(PlayerName("심지")),
                ),
            )
        }
    }

    @Test
    fun `딜러를 제외한 플레이어들의 수가 1명 미만이면 안된다`() {
        assertThrows<IllegalArgumentException> {
            Players(listOf())
        }
    }

    @Test
    fun `딜러를 제외한 플레이어들의 수가 6명 초과이면 안된다`() {
        assertThrows<IllegalArgumentException> {
            Players(
                listOf(
                    Player(PlayerName("심지")),
                    Player(PlayerName("해나")),
                    Player(PlayerName("팡태")),
                    Player(PlayerName("악어")),
                    Player(PlayerName("올리브")),
                    Player(PlayerName("서기")),
                    Player(PlayerName("채채")),
                ),
            )
        }
    }

    @Test
    fun `플레이어들과 딜러를 비교해서 플레이어들의 승리 여부를 계산한다`() {
        val dealer = Dealer()
        val player1 = Player(PlayerName("심지"))
        val player2 = Player(PlayerName("해나"))
        val player3 = Player(PlayerName("팡태"))

        dealer.state =
            Stay(
                CardHand(
                    Card(CardShape.HEART, CardNumber.EIGHT),
                    Card(CardShape.SPADE, CardNumber.JACK),
                ),
            )

        player1.state =
            Stay(
                CardHand(
                    Card(CardShape.HEART, CardNumber.SIX),
                    Card(CardShape.SPADE, CardNumber.JACK),
                ),
            )

        player2.state =
            Bust(
                CardHand(
                    Card(CardShape.DIAMOND, CardNumber.KING),
                    Card(CardShape.SPADE, CardNumber.JACK),
                    Card(CardShape.SPADE, CardNumber.TWO),
                ),
            )

        player3.state =
            BlackJack(
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.JACK),
                ),
            )
        val players = Players(listOf(player1, player2, player3))

        val actualPlayersWinning = players.calculatePlayersWinning(dealer)
        assertThat(actualPlayersWinning).isEqualTo(
            PlayerWinning(
                mapOf(
                    PlayerName("심지") to WinningResultStatus.DEFEAT,
                    PlayerName("해나") to WinningResultStatus.DEFEAT,
                    PlayerName("팡태") to WinningResultStatus.VICTORY,
                ),
            ),
        )
    }
}
