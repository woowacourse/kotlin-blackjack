package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createDealer(vararg numbers: Card): Dealer {
    return Dealer(DealerInfo(), hand = Hand(numbers.toMutableList()))
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
}

private fun createPlayer(vararg numbers: Card): Player {
    return Player(PlayerInfo(ParticipantName("leo"), ParticipantBetAmount(5000)), hand = Hand(numbers.toMutableList()))
}

class DealerTest {
    @Test
    fun `카드의 합이 버스트 기준점을 초과하지 않으면 Normal을 반환한다`() {
        val dealer = createDealer(Card(8), Card(9))
        assertThat(dealer.getState()).isInstanceOf(Normal::class.java)
    }

    @Test
    fun `카드의 합이 버스트 기준점을 초과하면 Bust를 반환한다`() {
        val dealer = createDealer(Card(8), Card(9), Card(10))
        assertThat(dealer.getState()).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드의 합이 블랙잭 기준점이고 카드의 갯수가 2개라면 Blackjack을 반환한다`() {
        val dealer = createDealer(Card(11), Card(10))
        assertThat(dealer.getState()).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `딜러가 카드의 합이 추가 카드를 뽑는 기준점을 넘을 경우 카드 뽑기를 안한다`() {
        val deck = CardDeck(cards = mutableListOf(Card(5)))
        val dealer = createDealer(Card(10), Card(9))

        dealer.playRound(
            { },
            deck,
        )

        assertThat(dealer.getState()).isInstanceOf(Normal::class.java)
    }

    @Test
    fun `딜러의 카드의 합이 카드를 뽑는 기준점보다 낮을 경우 카드(5)를 뽑은 후 카드의 합이 추가 카드를 뽑는 기준점을 넘을 경우 카드 뽑기를 멈춘다`() {
        val deck = CardDeck(cards = mutableListOf(Card(5)))
        val dealer = createDealer(Card(5), Card(9))

        dealer.playRound(
            { },
            deck,
        )

        assertThat(dealer.getState()).isInstanceOf(Normal::class.java)
    }

    @Test
    fun `딜러의 카드의 합이 카드를 뽑는 기준점보다 낮을 경우 카드(10)를 뽑은 후 카드의 합이 Bust 기준을 넘을 경우 딜러의 상태는 Bust가 되고, 카드 뽑기를 멈춘다`() {
        val deck = CardDeck(cards = mutableListOf(Card(10)))
        val dealer = createDealer(Card(5), Card(9))

        dealer.playRound(
            { },
            deck,
        )

        assertThat(dealer.getState()).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `딜러가 플레이어보다 점수가 낮으면 패배한다`() {
        val dealer = createDealer(Card(5), Card(9))
        val player = createPlayer(Card(8), Card(9))
        val gameState = dealer.calculateGameStateAgainst(player)

        assertThat(gameState).isEqualTo(Lose)
    }

    @Test
    fun `딜러의 카드 합이 플레이어보다 높을 때, 딜러가 승리한다`() {
        val dealer = createDealer(Card(10), Card(9))
        val player = createPlayer(Card(8), Card(9))
        val gameState = dealer.calculateGameStateAgainst(player)
        assertThat(gameState).isEqualTo(Win)
    }

    @Test
    fun `딜러가 블랙잭일때, 플레이어가 블랙잭이 아닐 경우 승리한다`() {
        val dealer = createDealer(Card(11), Card(10))
        val player = createPlayer(Card(11), Card(5))
        val gameState = dealer.calculateGameStateAgainst(player)
        assertThat(gameState).isEqualTo(WinWhenBlackjack)
    }

    @Test
    fun `딜러가 블랙잭일때, 플레이어가 블랙잭일 경우에는 비긴다`() {
        val dealer = createDealer(Card(11), Card(10))
        val player = createPlayer(Card(11), Card(10))
        val gameState = dealer.calculateGameStateAgainst(player)
        assertThat(gameState).isEqualTo(Tie)
    }

    @Test
    fun `딜러가 버스트일때, 플레이의 카드가 버스트일 경우에만 이긴다`() {
        val dealer = createDealer(Card(10), Card(9), Card(10))
        println(dealer.getState())
        val player = createPlayer(Card(10), Card(10), Card(10))
        val gameState = dealer.calculateGameStateAgainst(player)
        assertThat(gameState).isEqualTo(Win)
    }

    @Test
    fun `딜러가 버스트일때, 플레이의 카드가 버스트가 아닐 경우 진다`() {
        val dealer = createDealer(Card(10), Card(10), Card(2))
        val player = createPlayer(Card(11), Card(5))
        val gameState = dealer.calculateGameStateAgainst(player)
        assertThat(gameState).isEqualTo(Lose)
    }
}
