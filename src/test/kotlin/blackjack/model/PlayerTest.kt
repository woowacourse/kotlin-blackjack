package blackjack.model

import blackjack.model.TestUtils.Card
import blackjack.model.TestUtils.createCardDeckFrom
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createPlayer(
    name: String,
    vararg numbers: Card,
    betAmount: Double = 0.0,
): Player {
    val hand = Hand(numbers.toList())
    return Player(ParticipantName(name), hand, BetAmount(betAmount))
}

class PlayerTest {
    @Test
    fun `플레이어 카드 뽑기 테스트 - 카드의 합이 기준점이 넘지 않는 다면, 플레이어의 선택에 따라 카드를 더 뽑을 수 있다`() {
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
    fun `플레이어 카드 뽑기 테스트 - 카드의 합이 기준점에 도달하면, 플레이어의 선택에 상관없이 카드를 뽑을 수 없다`() {
        val deck = createCardDeckFrom(8, 5, 3, 9, 5)
        val player = createPlayer("yenny", Card(11), Card(10))
        player.playRound(deck, { true }, { })

        Assertions.assertThat(player.getCards().size).isEqualTo(2)
        Assertions.assertThat(player.getSumOfCards()).isEqualTo(21)
    }
}
