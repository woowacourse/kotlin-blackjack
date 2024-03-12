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

class DealerTest {
    @Test
    fun `딜러의 카드의 합이 카드를 뽑는 기준점을 넘는 경우, False를 반환한다`() {
        val dealer = createDealer(Card(8), Card(9))
        val threshold = 16
        assertThat(dealer.shouldDrawCardForDealer(threshold)).isFalse()
    }

    @Test
    fun `딜러의 카드의 합이 카드를 뽑는 기준점을 넘지 않는 경우, true를 반환한다`() {
        val dealer = createDealer(Card(7), Card(2))
        val threshold = 16
        assertThat(dealer.shouldDrawCardForDealer(threshold)).isTrue()
    }

    @Test
    fun `딜러가 카드의 합이 추가 카드를 뽑는 기준점을 넘을 경우 카드 뽑기를 안한다`() {
        val deck = CardDeck(cards = mutableListOf(Card(5)))
        val dealer = createDealer(Card(10), Card(9))

        dealer.playRound(
            { },
            deck,
        )

        assertThat(dealer.getState()).isEqualTo(State.Normal)
    }

    @Test
    fun `딜러의 카드의 합이 카드를 뽑는 기준점보다 낮을 경우 카드(5)를 뽑은 후 카드의 합이 추가 카드를 뽑는 기준점을 넘을 경우 카드 뽑기를 멈춘다`() {
        val deck = CardDeck(cards = mutableListOf(Card(5)))
        val dealer = createDealer(Card(5), Card(9))

        dealer.playRound(
            { },
            deck,
        )

        assertThat(dealer.getState()).isEqualTo(State.Normal)
    }

    @Test
    fun `딜러의 카드의 합이 카드를 뽑는 기준점보다 낮을 경우 카드(10)를 뽑은 후 카드의 합이 Bust 기준을 넘을 경우 딜러의 상태는 Bust가 되고, 카드 뽑기를 멈춘다`() {
        val deck = CardDeck(cards = mutableListOf(Card(10)))
        val dealer = createDealer(Card(5), Card(9))

        dealer.playRound(
            { },
            deck,
        )

        assertThat(dealer.getState()).isEqualTo(State.Bust)
    }

    @Test
    fun `딜러가 플레이어보다 점수가 낮으면 패배한다`() {
        val dealer = createDealer(Card(5), Card(9))
        val player = createPlayer(Card(8), Card(9))
        val winningState = dealer.calculateWinningStateWith(player)

        assertThat(winningState).isEqualTo(WinningState(0, 1))
    }
}
