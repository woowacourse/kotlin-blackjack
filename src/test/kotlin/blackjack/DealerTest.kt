package blackjack

import blackjack.model.deck.Deck
import blackjack.model.participant.Dealer
import blackjack.testmachine.BlackjackCardMachine
import blackjack.testmachine.BustCardMachine
import blackjack.testmachine.NormalCardMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck(NormalCardMachine())
        dealer = Dealer(deck)
    }

    @Test
    fun `딜러는 게임이 시작될 때 카드 2장을 받는다`() {
        assertThat(dealer.getAllCards().split(", ").size).isEqualTo(INIT_CARD_SIZE)
    }

    @Test
    fun `딜러 카드 합은 16이하이므로 카드 한 장을 더 받는다`() {
        assertThat(dealer.isAddCard()).isTrue
    }

    @Test
    fun `딜러는 카드를 추가로 한번 받을 경우 3장이다`() {
        dealer.addCard()
        assertThat(dealer.getAllCards().split(", ").size).isEqualTo(3)
    }

    @Test
    fun `딜러의 카드의 합이 21 초과일 시 버스트된다`() {
        val deck = Deck(BustCardMachine())
        val dealer = Dealer(deck)
        dealer.addCard()
        assertThat(dealer.isBust()).isTrue()
    }

    @Test
    fun `딜러는 블랙잭 여부를 반환할 수 있다(블랙잭일 경우 true)`() {
        val deck = Deck(BlackjackCardMachine())
        val dealer = Dealer(deck)
        assertThat(dealer.isBlackjack()).isTrue()
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
    }
}
