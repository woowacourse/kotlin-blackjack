package blackjack.model.participant.testState

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards
import blackjack.testmachine.BlackjackCardMachine
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitStateTest {
    @Test
    fun `처음 시작이 블랙잭이면 블랙잭을 반환한다`() {
        val deck = Deck(BlackjackCardMachine())
        val handCards = HandCards().also { it.add(deck.draw(2)) }
        val actual = InitState().nextTurn(handCards, false)
        assertThat(actual is Blackjack2).isTrue
    }

    @Test
    fun `블랙잭 여부에 관계없이, 유저가 Hit를 원한다면 Hit를 반환한다`() {
        val deck = Deck(BlackjackCardMachine())
        val handCards = HandCards().also { it.add(deck.draw(2)) }
        val actual = InitState().nextTurn(handCards, true)
        assertThat(actual is Hit).isTrue
    }

    @Test
    fun `블랙잭이 아니며, Hit를 원하지 않는다면 Stay를 반환한다`() {
        val deck = Deck(NormalCardMachine())
        val handCards = HandCards().also { it.add(deck.draw(2)) }
        val actual = InitState().nextTurn(handCards, false)
        assertThat(actual is Stay).isTrue
    }
}
