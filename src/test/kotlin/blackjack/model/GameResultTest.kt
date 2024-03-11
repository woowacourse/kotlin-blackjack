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
            Player("꼬상").apply {
                draw(Card(Denomination.ACE, Suit.SPADE))
                draw(Card(Denomination.KING, Suit.HEART))
            }
        val winPlayers = listOf(player)
        val gameManager =
            GameManager(
                dealer = dealer,
                players = winPlayers,
            )
        gameManager.judgeBlackJackScores()
        val dealerResults = gameManager.getDealerResults()
        assertThat(dealerResults.getOrDefault(Result.LOSE, 0)).isEqualTo(1)
        val playerResults = gameManager.getPlayerResults()
        assertThat(playerResults.getOrDefault(player, Result.LOSE)).isEqualTo(Result.WIN)
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
            Player("누누").apply {
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
        val gameManager =
            GameManager(
                dealer = dealer,
                players = drawPlayers,
            )
        gameManager.judgeBlackJackScores()
        val dealerResults = gameManager.getDealerResults()
        assertThat(dealerResults.getOrDefault(Result.DRAW, 0)).isEqualTo(1)
        val playerResults = gameManager.getPlayerResults()
        assertThat(playerResults.getOrDefault(player, Result.WIN)).isEqualTo(Result.DRAW)
    }

    @Test
    fun `게임 결과에 따라 승 판단 테스트`() {
        val dealer =
            Dealer().apply {
                draw(Card(Denomination.SEVEN, Suit.SPADE))
                draw(Card(Denomination.SEVEN, Suit.HEART))
            }
        val player =
            Player("누누").apply {
                draw(Card(Denomination.SIX, Suit.SPADE))
                draw(Card(Denomination.SIX, Suit.HEART))
            }
        val losePlayers = listOf(player)
        val gameManager =
            GameManager(
                dealer = dealer,
                players = losePlayers,
            )
        gameManager.judgeBlackJackScores()
        val dealerResults = gameManager.getDealerResults()
        assertThat(dealerResults.getOrDefault(Result.WIN, 0)).isEqualTo(1)
        val playerResults = gameManager.getPlayerResults()
        assertThat(playerResults.getOrDefault(player, Result.WIN)).isEqualTo(Result.LOSE)
    }
}
