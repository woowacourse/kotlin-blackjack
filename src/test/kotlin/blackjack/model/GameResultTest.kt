package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var gameResult: GameResult
    private lateinit var dealer: Dealer
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        dealer =
            Dealer().apply {
                draw(Card(Denomination.JACK, Suit.SPADE))
                draw(Card(Denomination.JACK, Suit.HEART))
            }

        players =
            listOf(
                Player("꼬상").apply {
                    draw(Card(Denomination.ACE, Suit.SPADE))
                    draw(Card(Denomination.KING, Suit.HEART))
                },
                Player("누누").apply {
                    draw(Card(Denomination.KING, Suit.SPADE))
                    draw(Card(Denomination.KING, Suit.HEART))
                },
                Player("채드").apply {
                    draw(Card(Denomination.SEVEN, Suit.SPADE))
                    draw(Card(Denomination.TWO, Suit.HEART))
                },
            )
        gameResult = GameResult()
    }

    @Test
    fun `게임 결과에 따라 딜러 결과 일치 테스트`() {
        gameResult.calculateResult(dealer, players)
        val dealerResults = gameResult.getDealerResults()

        assertThat(dealerResults.getOrDefault(Result.WIN, 0)).isEqualTo(1)
        assertThat(dealerResults.getOrDefault(Result.DRAW, 0)).isEqualTo(1)
        assertThat(dealerResults.getOrDefault(Result.LOSE, 0)).isEqualTo(1)
    }

    @Test
    fun `게임 결과에 따라 플레이어 결과 일치 테스트`() {
        gameResult.calculateResult(dealer, players)
        val playerResults = gameResult.getPlayerResults()

        assertThat(playerResults[players[0]]).isEqualTo(Result.WIN)
        assertThat(playerResults[players[1]]).isEqualTo(Result.DRAW)
        assertThat(playerResults[players[2]]).isEqualTo(Result.LOSE)
    }
}
