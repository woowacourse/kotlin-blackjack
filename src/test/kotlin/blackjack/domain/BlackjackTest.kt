package blackjack.domain

import blackjack.domain.Rank.AceRank
import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    @Test
    fun `게임을 시작하면 딜러는 한 장의 카드를 지급받는다`() {
        val dealer = Dealer(emptyList(), RandomShuffler)
        val game = Blackjack(dealer, emptyList())
        game.start()
        assertThat(dealer.cards.size).isEqualTo(1)
    }

    @Test
    fun `게임을 시작하면 플레이어는 두 장의 카드를 지급받는다`() {
        val gio = Player("Gio")
        val eden = Player("Eden")
        val players = listOf(gio, eden)
        val dealer = Dealer(players, RandomShuffler)
        val game = Blackjack(dealer, players)
        game.start()
        assertThat(gio.cards.size).isEqualTo(2)
        assertThat(eden.cards.size).isEqualTo(2)
    }

    @Test
    fun `모든 플레이어들의 결정이 끝나면 딜러는 숫자 합이 17 이상이 될 수 있을 때까지 카드를 받는다`() {
        val gio = Player("Gio")
        val eden = Player("Eden")
        val players = listOf(gio, eden)
        val dealer = Dealer(players, RandomShuffler)
        val game = Blackjack(dealer, players)
        game.waitForPlayers()
        assertThat(dealer.getScore()).isGreaterThanOrEqualTo(17)
    }

    @Test
    fun `게임을 완료한 후 플레이어, 딜러의 승패를 알 수 있다`() {
        val gio =
            Player("Gio").apply {
                draw(Card(NumberRank.TEN, Suit.SPADE))
                draw(Card(NumberRank.TEN, Suit.SPADE))
            }

        val eden =
            Player("Eden").apply {
                draw(Card(NumberRank.NINE, Suit.SPADE))
                draw(Card(NumberRank.NINE, Suit.SPADE))
            }

        val players = listOf(gio, eden)
        val dealer = Dealer(players, RandomShuffler)
        val blackjack = Blackjack(dealer, players)

        dealer.getCards(listOf(Card(NumberRank.NINE, Suit.SPADE), Card(NumberRank.TEN, Suit.SPADE)))
        blackjack.finish()
        assertThat(gio.playerState).isEqualTo(PlayerState.WIN)
        assertThat(eden.playerState).isEqualTo(PlayerState.LOSE)
        assertThat(dealer.playerStates).isEqualTo(listOf(PlayerState.LOSE, PlayerState.WIN))
    }

    @Test
    fun `딜러의 카드 숫자 합이 21을 초과할 수 밖에 없을 경우 남은 플레이어는 전부 승리한다`() {
        val eden = Player("Eden")
        val gio = Player("Gio")
        val players: List<Player> = listOf(eden, gio)
        val dealer = Dealer(players, RandomShuffler)
        dealer.getCards(
            listOf(
                Card(NumberRank.TEN, Suit.SPADE),
                Card(NumberRank.TEN, Suit.SPADE),
                Card(NumberRank.TEN, Suit.SPADE),
            ),
        )
        val blackjack = Blackjack(dealer, players)
        blackjack.finish()
        assertThat(eden.playerState).isEqualTo(PlayerState.WIN)
        assertThat(gio.playerState).isEqualTo(PlayerState.WIN)
    }

    @Test
    fun `아직 승패가 결정되지 않았다면, 딜러와 플레이어 중 카드의 합이 21에 가까운 사람이 이긴다`() {
        val eden =
            Player("Eden").apply {
                draw(Card(AceRank, Suit.SPADE))
                draw(Card(NumberRank.TEN, Suit.CLOVER))
            }
        val gio =
            Player("Gio").apply {
                draw(Card(NumberRank.TEN, Suit.DIAMOND))
                draw(Card(NumberRank.NINE, Suit.SPADE))
            }
        val players: List<Player> = listOf(eden, gio)
        val dealer =
            Dealer(players, RandomShuffler).apply {
                getCards(
                    listOf(
                        Card(NumberRank.TEN, Suit.HEART),
                        Card(NumberRank.TEN, Suit.DIAMOND),
                    ),
                )
            }
        val blackjack = Blackjack(dealer, players)
        blackjack.finish()
        assertThat(eden.playerState).isEqualTo(PlayerState.WIN)
        assertThat(gio.playerState).isEqualTo(PlayerState.LOSE)
    }

    @Test
    fun `딜러와 플레이어의 숫자가 같다면, 무승부로 처리한다`() {
        val gio =
            Player("Gio").apply {
                draw(Card(NumberRank.TEN, Suit.DIAMOND))
                draw(Card(NumberRank.NINE, Suit.SPADE))
            }
        val players: List<Player> = listOf(gio)
        val dealer =
            Dealer(players, RandomShuffler).apply {
                getCard(Card(NumberRank.TEN, Suit.HEART))
                getCard(Card(NumberRank.NINE, Suit.DIAMOND))
            }
        val blackjack = Blackjack(dealer, players)
        blackjack.finish()
        assertThat(dealer.playerStates).contains(PlayerState.DRAW)
        assertThat(gio.playerState).isEqualTo(PlayerState.DRAW)
    }
}
