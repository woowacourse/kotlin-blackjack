package blackjack.domain

import blackjack.domain.Rank.AceRank
import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    private fun Dealer(
        player: Player,
        shuffler: Shuffler,
    ) = Dealer(listOf(player), shuffler)

    private fun Participant.draw(vararg cards: Card) {
        cards.forEach { card ->
            draw(card)
        }
    }

    @Test
    fun `게임을 시작하면 딜러는 한 장의 카드를 지급받는다`() {
        val dealer = Dealer(emptyList(), { it })
        val game = Blackjack(dealer)
        game.dealCards()
        assertThat(dealer.cards.size).isEqualTo(1)
    }

    @Test
    fun `게임을 시작하면 플레이어는 두 장의 카드를 지급받는다`() {
        val gio = Player("Gio")
        val dealer = Dealer(gio, { it })
        val game = Blackjack(dealer)
        game.dealCards()
        assertThat(gio.cards.size).isEqualTo(2)
    }

    @Test
    fun `모든 플레이어들의 결정이 끝나면 딜러는 숫자 합이 17 이상이 될 수 있을 때까지 카드를 받는다`() {
        val dealer = Dealer(emptyList(), { it })
        val game = Blackjack(dealer)
        game.startDealerTurn()
        assertThat(dealer.score).isGreaterThanOrEqualTo(17)
    }

    @Test
    fun `게임을 완료한 후 플레이어, 딜러의 승패를 알 수 있다`() {
        val winner =
            Player("winner").apply {
                draw(Card(NumberRank.TEN, Suit.SPADE))
                draw(Card(NumberRank.TEN, Suit.SPADE))
            }

        val loser =
            Player("loser").apply {
                draw(Card(NumberRank.NINE, Suit.SPADE))
                draw(Card(NumberRank.NINE, Suit.SPADE))
            }
        val players = listOf(winner, loser)
        val dealer =
            Dealer(players, { it }).apply {
                draw(Card(NumberRank.NINE, Suit.SPADE), Card(NumberRank.TEN, Suit.SPADE))
            }
        val blackjack = Blackjack(dealer)
        blackjack.setResult()
        assertThat(dealer.dealerResults).isEqualTo(listOf(ParticipantState.LOSE, ParticipantState.WIN))
    }

    @Test
    fun `딜러의 카드 숫자 합이 21을 초과할 수 밖에 없을 경우 남은 플레이어는 전부 승리한다`() {
        val player = Player("player")
        val dealer = Dealer(listOf(player), RandomShuffler)
        dealer.draw(
            Card(NumberRank.TEN, Suit.SPADE),
            Card(NumberRank.TEN, Suit.SPADE),
            Card(NumberRank.TEN, Suit.SPADE),
        )
        val blackjack = Blackjack(dealer)
        blackjack.setResult()
        assertThat(player)
    }

    @Test
    fun `아직 승패가 결정되지 않았다면, 딜러와 플레이어 중 카드의 합이 21에 가까운 사람이 이긴다`() {
        val winner =
            Player("winner").apply {
                draw(Card(AceRank, Suit.SPADE))
                draw(Card(NumberRank.TEN, Suit.CLOVER))
            }
        val loser =
            Player("loser").apply {
                draw(Card(NumberRank.TEN, Suit.DIAMOND))
                draw(Card(NumberRank.NINE, Suit.SPADE))
            }
        val players: List<Player> = listOf(winner, loser)
        val dealer =
            Dealer(players, RandomShuffler).apply {
                draw(
                    Card(NumberRank.TEN, Suit.HEART),
                    Card(NumberRank.TEN, Suit.DIAMOND),
                )
            }
        val blackjack = Blackjack(dealer)
        blackjack.setResult()
        assertThat(winner.state).isEqualTo(ParticipantState.WIN)
        assertThat(loser.state).isEqualTo(ParticipantState.LOSE)
    }

    @Test
    fun `딜러와 플레이어의 숫자가 같다면, 무승부로 처리한다`() {
        val drawer =
            Player("drawer").apply {
                draw(Card(NumberRank.TEN, Suit.DIAMOND))
            }
        val players: List<Player> = listOf(drawer)
        val dealer =
            Dealer(players, RandomShuffler).apply {
                draw(Card(NumberRank.TEN, Suit.HEART))
            }
        val blackjack = Blackjack(dealer)
        blackjack.setResult()
        assertThat(dealer.dealerResults).contains(ParticipantState.DRAW)
        assertThat(drawer.state).isEqualTo(ParticipantState.DRAW)
    }
}
