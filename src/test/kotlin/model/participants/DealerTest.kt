package model.participants

import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.makeDealer
import model.makePlayer
import model.makeTestDeck
import model.result.Point.Companion.compareTo
import model.result.ResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var testDeck: Deck
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        testDeck =
            makeTestDeck(
                Card(ValueType.TWO, MarkType.SPADE),
                Card(ValueType.JACK, MarkType.SPADE),
                Card(ValueType.QUEEN, MarkType.SPADE),
            )
        player = makePlayer()
        dealer = makeDealer()
    }

    @Test
    fun `play가 끝나면 핸드의 합은 17 이상이다`() {
        dealer.play(testDeck)
        assertThat(dealer.participantState.hand.point >= 17).isTrue
    }

    @Test
    fun `Dealer에서 게임 결과를 판단할 수 있다`() {
        dealer.hit(testDeck.pop())
        player.hit(testDeck.pop())

        val result = dealer.judge(player)
        assertThat(result).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `Player와 Dealer 모두 bust라면 Dealer의 승리`() {
        dealer.participantState = ParticipantState.Bust(Hand())
        player.participantState = ParticipantState.Bust(Hand())

        val result = dealer.judge(player)
        assertThat(result).isEqualTo(ResultType.WIN)
    }
}
