package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createDealer(vararg numbers: Card): Dealer {
    return Dealer(hand = Hand(numbers.toMutableList()))
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
}

private fun createPlayer(vararg numbers: Card): Player {
    return Player(ParticipantName("leo"), hand = Hand(numbers.toMutableList()))
}

class PlayerTest {
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

        val winningState = player.calculateWinningStateAgainst(dealer)

        assertThat(winningState).isEqualTo(WinningState(1, 0))
    }
}
