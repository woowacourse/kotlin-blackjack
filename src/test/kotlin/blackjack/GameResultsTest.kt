package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.gameResult.GameResultStatus
import blackjack.domain.gameResult.GameResults
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultsTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun clear() {
        dealer = Dealer()
        player = Player("a")
    }

    private fun setDealerCard(vararg card: Card) {
        card.forEach { dealer.addCard(it) }
    }

    private fun setPlayerCard(vararg card: Card) {
        card.forEach { player.addCard(it) }
    }

    @Test
    fun `점수를 판별할 때, 플레이어가 딜러보다 더 점수가 높을 때 이긴다고 판단한다`() {
        setDealerCard(
            Card.of(Rank.NINE, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )

        setPlayerCard(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )
        val gameResults = GameResults(dealer, listOf(player))
        assertThat(gameResults.judgePlayerResult(player)).isEqualTo(GameResultStatus.PLAYER_WIN)
    }

    @Test
    fun `점수를 판별할 때, 플레이어가 딜러보다 더 점수가 높을 때 진다고 판단한다`() {
        setDealerCard(
            Card.of(Rank.ACE, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )

        setPlayerCard(
            Card.of(Rank.TEN, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )
        val gameResults = GameResults(dealer, listOf(player))
        assertThat(gameResults.judgePlayerResult(player)).isEqualTo(GameResultStatus.PLAYER_LOSE)
    }

    @Test
    fun `점수를 판별할 때, 플레이어와 딜러의 점수가 같다면 무승부라고 판단한다`() {
        setDealerCard(
            Card.of(Rank.ACE, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
        )

        setPlayerCard(
            Card.of(Rank.TWO, Suit.CLUB),
            Card.of(Rank.TEN, Suit.SPADE),
            Card.of(Rank.NINE, Suit.SPADE),
        )
        val gameResults = GameResults(dealer, listOf(player))
        assertThat(gameResults.judgePlayerResult(player)).isEqualTo(GameResultStatus.DRAW)
    }
}
