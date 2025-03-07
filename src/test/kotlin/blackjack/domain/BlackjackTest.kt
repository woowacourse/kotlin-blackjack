package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    @Test
    fun `게임을 시작하면 딜러는 한 장의 카드를 지급받는다`() {
        val dealer = Dealer(emptyList())
        val game = Blackjack(dealer, emptyList())
        game.start()
        assertThat(dealer.getCountOfCards()).isEqualTo(1)
    }

    @Test
    fun `게임을 시작하면 플레이어는 두 장의 카드를 지급받는다`() {
        val gio = Player("Gio")
        val eden = Player("Eden")
        val players = listOf(gio, eden)
        val dealer = Dealer(players)
        val game = Blackjack(dealer, players)
        game.start()
        assertThat(gio.getCountOfCards()).isEqualTo(2)
        assertThat(eden.getCountOfCards()).isEqualTo(2)
    }

    @Test
    fun `모든 플레이어들의 결정이 끝나면 딜러는 숫자 합이 17 이상이 될 수 있을 때까지 카드를 받는다`() {
        val gio = Player("Gio")
        val eden = Player("Eden")
        val players = listOf(gio, eden)
        val dealer = Dealer(players)
        val game = Blackjack(dealer, players)
        game.waitForPlayers()
        assertThat(dealer.getScore()).isGreaterThanOrEqualTo(17)
    }

    @Test
    fun `게임을 완료한 후 플레이어, 딜러의 승패를 알 수 있다`() {
        val gio = Player("Gio")
        val eden = Player("Eden")
        val players = listOf(gio, eden)
        val dealer = Dealer(players)
        val blackjack = Blackjack(dealer, players)
        gio.getCards(listOf(Card(Number(10), Suit.SPADE), Card(Number(10), Suit.SPADE)))
        eden.getCards(listOf(Card(Number(9), Suit.SPADE), Card(Number(9), Suit.SPADE)))
        dealer.getCards(listOf(Card(Number(9), Suit.SPADE), Card(Number(10), Suit.SPADE)))
        blackjack.finish()
        assertThat(gio.result).isEqualTo(Result.WIN)
        assertThat(eden.result).isEqualTo(Result.LOSE)
        assertThat(dealer.results).isEqualTo(listOf(Result.LOSE, Result.WIN))
    }

    @Test
    fun `딜러의 카드 숫자 합이 21을 초과할 수 밖에 없을 경우 남은 플레이어는 전부 승리한다`() {
        val eden = Player("Eden")
        val gio = Player("Gio")
        val players: List<Player> = listOf(eden, gio)
        val dealer = Dealer(players)
        dealer.getCards(
            listOf(
                Card(Number(10), Suit.SPADE),
                Card(Number(10), Suit.SPADE),
                Card(Number(10), Suit.SPADE),
            ),
        )
        val blackjack = Blackjack(dealer, players)
        blackjack.finish()
        assertThat(eden.result).isEqualTo(Result.WIN)
        assertThat(gio.result).isEqualTo(Result.WIN)
    }

    @Test
    fun `아직 승패가 결정되지 않았다면, 딜러와 플레이어 중 카드의 합이 21에 가까운 사람이 이긴다`() {
        val eden = Player("Eden")
        val gio = Player("Gio")
        val players: List<Player> = listOf(eden, gio)
        val dealer = Dealer(players)

        eden.getCards(
            listOf(
                Card(Ace(), Suit.SPADE),
                Card(Number(10), Suit.CLOVER),
            ),
        )
        gio.getCards(
            listOf(
                Card(Number(10), Suit.DIAMOND),
                Card(Number(9), Suit.SPADE),
            ),
        )
        dealer.getCards(
            listOf(
                Card(Number(10), Suit.HEART),
                Card(Number(10), Suit.DIAMOND),
            ),
        )
        val blackjack = Blackjack(dealer, players)
        blackjack.finish()
        assertThat(eden.result).isEqualTo(Result.WIN)
        assertThat(gio.result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러와 플레이어의 숫자가 같다면, 무승부로 처리한다`() {
        val gio = Player("Gio")
        val players: List<Player> = listOf(gio)
        val dealer = Dealer(players)

        gio.getCards(
            listOf(
                Card(Number(10), Suit.DIAMOND),
                Card(Number(9), Suit.SPADE),
            ),
        )
        dealer.getCards(
            listOf(
                Card(Number(10), Suit.HEART),
                Card(Number(9), Suit.DIAMOND),
            ),
        )
        val blackjack = Blackjack(dealer, players)
        blackjack.finish()
        assertThat(dealer.results).contains(Result.DRAW)
        assertThat(gio.result).isEqualTo(Result.DRAW)
    }
}
