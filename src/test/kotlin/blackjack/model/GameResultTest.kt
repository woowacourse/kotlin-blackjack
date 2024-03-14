package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `게임 결과에 따라 패 판단 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.JACK, Suit.SPADE))
                draw(Card(Denomination.JACK, Suit.HEART))
            }
        val player =
            Player(Wallet("꼬상")).apply {
                draw(Card(Denomination.ACE, Suit.SPADE))
                draw(Card(Denomination.KING, Suit.HEART))
            }
        val winPlayers = listOf(player)
        val participants =
            Participants(
                dealer = dealer,
                players = winPlayers,
            )
        dealer.transitionToStayState()
        val gameManager =
            GameManager(
                participants = participants,
            )
        val gameResult = gameManager.calculateGameResult()
        assertThat(gameResult.getDealerResult(Result.LOSE)).isEqualTo(1)
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.WIN)
    }

    @Test
    fun `게임 결과에 따라 무 판단 테스트`() {
        val dealer =
            Dealer().apply {
                draw(
                    Card(
                        Denomination.SEVEN,
                        Suit.SPADE,
                    ),
                )
                draw(
                    Card(
                        Denomination.SEVEN,
                        Suit.HEART,
                    ),
                )
            }
        val player =
            Player(Wallet("누누")).apply {
                draw(
                    Card(
                        Denomination.SEVEN,
                        Suit.DIAMOND,
                    ),
                )
                draw(
                    Card(
                        Denomination.SEVEN,
                        Suit.CLOVER,
                    ),
                )
            }
        val drawPlayers = listOf(player)
        val participants =
            Participants(
                dealer = dealer,
                players = drawPlayers,
            )
        dealer.transitionToStayState()
        player.transitionToStayState()
        val gameManager =
            GameManager(
                participants = participants,
            )
        val gameResult = gameManager.calculateGameResult()
        assertThat(gameResult.getDealerResult(Result.DRAW)).isEqualTo(1)
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.DRAW)
    }

    @Test
    fun `게임 결과에 따라 승 판단 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.SEVEN, Suit.SPADE))
                draw(Card(Denomination.SEVEN, Suit.HEART))
            }
        val player =
            Player(Wallet("누누")).apply {
                draw(Card(Denomination.SIX, Suit.SPADE))
                draw(Card(Denomination.SIX, Suit.HEART))
            }
        val losePlayers = listOf(player)
        val participants =
            Participants(
                dealer = dealer,
                players = losePlayers,
            )
        dealer.transitionToStayState()
        player.transitionToStayState()
        val gameManager =
            GameManager(
                participants = participants,
            )
        val gameResult = gameManager.calculateGameResult()
        assertThat(gameResult.getDealerResult(Result.WIN)).isEqualTo(1)
        assertThat(gameResult.getPlayerResult(player)).isEqualTo(Result.LOSE)
    }
}
