package blackjack.model.deck

import blackjack.model.participant.state.Blackjack2
import blackjack.model.participant.state.Hit
import blackjack.model.participant.state.InitState
import blackjack.model.participant.state.Stay
import blackjack.testmachine.BlackjackCardMachine
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandCardsTest {
    private lateinit var handCards: HandCards
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        handCards = HandCards()
        deck = Deck(NormalCardMachine())
    }

    @Test
    fun `유저가 Hit를 원하면 새로운 카드를 뽑는다`() {
        assertThat(handCards.cards.size).isEqualTo(INITIAL_SIZE)

        handCards.playTurn(true, deck::draw)
        assertThat(handCards.cards.size).isEqualTo(INITIAL_SIZE + 1)
    }

    @Test
    fun `초기 카드가 블랙잭이면, state가 blackjack으로 변한다`() {
        val deck = Deck(BlackjackCardMachine())
        assertThat(handCards.state is InitState).isTrue

        handCards.initCard(deck.draw(2))
        assertThat(handCards.state is Blackjack2)
    }

    @Test
    fun `유저가 Stay를 원하면, 카드의 상태는 Hit로 변한다`() {
        assertThat(handCards.state is InitState).isTrue

        handCards.playTurn(true, deck::draw)
        assertThat(handCards.state is Hit).isTrue
    }

    @Test
    fun `유저가 Stay를 원하면, 카드의 상태는 Stay로 변한다`() {
        assertThat(handCards.state is InitState).isTrue

        handCards.playTurn(false, deck::draw)
        assertThat(handCards.state is Stay).isTrue
    }

    companion object {
        private const val INITIAL_SIZE = 0
    }
}
