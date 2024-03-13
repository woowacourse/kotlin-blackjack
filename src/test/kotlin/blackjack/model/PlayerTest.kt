package blackjack.model

import blackjack.model.TestUtils.Card
import blackjack.model.TestUtils.createCardDeckFrom
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createPlayer(
    name: String,
    vararg numbers: Card,
    betAmount: Double = 1_000.0,
): Player {
    val hand = Hand(numbers.toList())
    return Player(ParticipantName(name), hand, BetAmount(betAmount))
}

class PlayerTest {
    @Test
    fun `플레이어 카드 뽑기 테스트 - 처음 제공된 두 장의 카드 합이 기준점에 도달할 경우, 플레이어는 카드를 뽑을 수 없다`() {
        val deck = createCardDeckFrom(8, 5, 3, 9, 5)
        val player = createPlayer("yenny", Card(11), Card(10))
        player.playRound(deck, { true }, { })

        assertThat(player.getCards().size).isEqualTo(2)
        assertThat(player.getSumOfCards()).isEqualTo(21)
    }

    @Test
    fun `플레이어 카드 뽑기 테스트 - 카드의 합이 기준점이 넘지 않으면, 플레이어의 선택에 따라 카드를 더 뽑을 수 있다`() {
        val deck = createCardDeckFrom(8, 5, 3, 9, 5)
        val player = createPlayer("yenny", Card(7), Card(10))
        player.playRound(deck, { true }, { })

        assertThat(player.getCards().size).isEqualTo(3)
        assertThat(player.getSumOfCards()).isEqualTo(22)
    }

    @Test
    fun `플레이어 카드 뽑기 테스트 - 카드의 합이 기준점이 넘지 않아도, 플레이어의 선택에 따라 카드를 더 뽑지 않을 수 있다`() {
        val deck = createCardDeckFrom(8, 5, 3, 9, 5)
        val player = createPlayer("yenny", Card(7), Card(10))
        player.playRound(deck, { false }, { })

        assertThat(player.getCards().size).isEqualTo(2)
        assertThat(player.getSumOfCards()).isEqualTo(17)
    }

    @Test
    fun `플레이어 카드 뽑기 테스트 - 카드 합이 기준점을 초과하는 경우, 플레이어는 카드를 뽑을 수 없다`() {
        val deck = createCardDeckFrom(8, 5, 3, 9, 5, 3)
        val player = createPlayer("yenny", Card(8), Card(10))
        player.playRound(deck, { true }, { })

        assertThat(player.getCards().size).isEqualTo(4)
        assertThat(player.getSumOfCards()).isEqualTo(26)
    }

    @Test
    fun `게임에서 질 경우, 배팅 금액을 모두 잃게 된다`() {
        val betAmount = 1000.0
        val player = createPlayer("yenny", Card(6), Card(8), betAmount = betAmount).apply { finishRound() }
        val result = player.calculateProfit(WinningResult.LOSE)
        assertThat(result).isEqualTo(-1000.0)
    }

    @Test
    fun `블랙잭으로 승리할 경우, 베팅 금액의 특정 배수 만큼 수익을 얻는다`() {
        val betAmount = 1000.0
        val player = createPlayer("yenny", Card(10), Card(11), betAmount = betAmount).apply { finishRound() }
        val result = player.calculateProfit(WinningResult.WIN)
        assertThat(result).isEqualTo(1500.0)
    }

    @Test
    fun `블랙잭은 아니지만 딜러보다 카드 점수가 높아서 이긴 경우, 베팅 금액 만큼 수익을 얻는다`() {
        val betAmount = 1000.0
        val player = createPlayer("yenny", Card(8), Card(11), betAmount = betAmount).apply { finishRound() }
        val result = player.calculateProfit(WinningResult.WIN)
        assertThat(result).isEqualTo(1000.0)
    }

    @Test
    fun `딜러와 동점인 경우, 플레이어는 베팅한 금액을 돌려받는다`() {
        val betAmount = 1000.0
        val player = createPlayer("yenny", Card(10), Card(11), betAmount = betAmount).apply { finishRound() }
        val result = player.calculateProfit(WinningResult.DRAW)
        assertThat(result).isEqualTo(0.0)
    }
}
