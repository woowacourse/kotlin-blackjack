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
    return Player(PlayerInfo(ParticipantName("leo"), ParticipantBetAmount(1000)), hand = Hand(numbers.toMutableList()))
}

private fun createPlayerWithBetAmount(
    vararg numbers: Card,
    betAmount: Int,
): Player {
    return Player(
        PlayerInfo(ParticipantName("leo"), ParticipantBetAmount(betAmount)),
        hand = Hand(numbers.toMutableList()),
    )
}

class PlayerTest {
    @Test
    fun `카드의 합이 버스트 기준점을 초과하지 않으면 Normal을 반환한다`() {
        val dealer = createPlayer(Card(8), Card(9))
        assertThat(dealer.getState()).isInstanceOf(Normal::class.java)
    }

    @Test
    fun `카드의 합이 버스트 기준점을 초과하면 Bust를 반환한다`() {
        val dealer = createPlayer(Card(8), Card(9), Card(10))
        assertThat(dealer.getState()).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드의 합이 블랙잭 기준점이고 카드의 갯수가 2개라면 Blackjack을 반환한다`() {
        val dealer = createPlayer(Card(11), Card(10))
        assertThat(dealer.getState()).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `(플레이어는 항상 "y"라고 대답한다고 가정) 플레이어가 추가 카드를 받아 합이 버스트 기준점을 초과하면 버스트 상태가 된다`() {
        val deck = CardDeck(cards = mutableListOf(Card(5)))
        val player = createPlayer(Card(8), Card(9))

        player.playRound(
            { true },
            { },
            deck,
        )

        assertThat(player.getState()).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `플레이어의 카드 합이 딜러보다 높을 때, 플레이어가 승리한다`() {
        val dealer = createDealer(Card(5), Card(9))
        val player = createPlayer(Card(8), Card(9))
        val gameState = player.calculateGameStateAgainst(dealer)
        assertThat(gameState).isEqualTo(Win)
    }

    @Test
    fun `플레이어의 카드 합이 딜러보다 낮을 때, 플레이어가 패배한다`() {
        val player = createDealer(Card(5), Card(9))
        val dealer = createPlayer(Card(8), Card(9))
        val gameState = player.calculateGameStateAgainst(dealer)
        assertThat(gameState).isEqualTo(Lose)
    }

    @Test
    fun `플레이어가 블랙잭일때, 딜러가 블랙잭이 아닐 경우 승리한다`() {
        val player = createPlayer(Card(11), Card(10))
        val dealer = createDealer(Card(11), Card(9))
        val gameState = player.calculateGameStateAgainst(dealer)
        assertThat(gameState).isEqualTo(WinWhenBlackjack)
    }

    @Test
    fun `플레이어가 블랙잭일때, 딜러가 블랙잭일 경우에는 비긴다`() {
        val player = createPlayer(Card(11), Card(10))
        val dealer = createDealer(Card(11), Card(10))
        val gameState = player.calculateGameStateAgainst(dealer)
        assertThat(gameState).isEqualTo(Tie)
    }

    @Test
    fun `플레이어가 버스트일때, 딜러의 카드와 상관없이 패배한다`() {
        val player = createPlayer(Card(10), Card(10), Card(2))
        val dealer = createDealer(Card(10), Card(10), Card(10))
        val gameState = player.calculateGameStateAgainst(dealer)
        assertThat(gameState).isEqualTo(Lose)
    }

    @Test
    fun `플레이어가 블랙잭일때, 딜러가 블랙잭이 아닐 경우 배팅금액의 일점오배를 받는다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(11), betAmount = 1000)
        val dealer = createDealer(Card(10), Card(10), Card(10))
        assertThat(player.calculateProfitAgainst(opponent = dealer)).isEqualTo(1000 * 1.5)
    }

    @Test
    fun `플레이어가 블랙잭일때, 딜러가 블랙잭일 경우 0원을 받는다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(11), betAmount = 1000)
        val dealer = createDealer(Card(10), Card(11))
        assertThat(player.calculateProfitAgainst(opponent = dealer)).isEqualTo(0.0)
    }

    @Test
    fun `플레이어가 버스트일때, 딜러와 상관 없이 배팅금액을 잃는다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(10), Card(5), betAmount = 1000)
        val dealer = createDealer(Card(10), Card(10), Card(10))
        assertThat(player.calculateProfitAgainst(opponent = dealer)).isEqualTo(1000 * (-1.0))
    }

    @Test
    fun `플레이어가 노말일때, 딜러와 패의 합이 같으면 0원을 받는다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(10), betAmount = 1000)
        val dealer = createDealer(Card(10), Card(10))
        assertThat(player.calculateProfitAgainst(opponent = dealer)).isEqualTo(0.0)
    }

    @Test
    fun `플레이어가 노말일때, 딜러가 패의 합이 높으면 배팅금액을 잃는다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(9), betAmount = 1000)
        val dealer = createDealer(Card(10), Card(10))
        assertThat(player.calculateProfitAgainst(opponent = dealer)).isEqualTo(1000 * (-1.0))
    }

    @Test
    fun `플레이어가 노말일때, 딜러가 패의 합이 낮으면 배팅금액을 받는다`() {
        val player = createPlayerWithBetAmount(Card(10), Card(9), betAmount = 1000)
        val dealer = createDealer(Card(10), Card(8))
        assertThat(player.calculateProfitAgainst(opponent = dealer)).isEqualTo(1000 * (1.0))
    }
}
