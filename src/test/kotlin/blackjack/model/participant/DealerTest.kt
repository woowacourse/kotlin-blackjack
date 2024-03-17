package blackjack.model.participant

import blackjack.model.deck.Deck
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
        dealer = Dealer.withInitCards(deck)
    }

    @Test
    fun `딜러는 게임이 시작될 때 카드 2장을 받는다`() {
        assertThat(dealer.getAllCards().size).isEqualTo(INIT_CARD_SIZE)
    }

    @Test
    fun `딜러 카드 합은 16이하이므로 카드 한장을 더 받을 수 있다`() {
        assertThat(dealer.add(deck.draw(1))).isTrue
    }

    @Test
    fun `딜러는 카드를 추가로 받을 수 있다`() {
        dealer.add(deck.draw(1))
        assertThat(dealer.getAllCards().size).isEqualTo(3)
    }

    @Test
    fun `딜러의 카드의 합이 21 초과일 시 버스트된다`() {
        val deck = Deck(BustCardMachine())
        val dealer = Dealer.withInitCards(deck)
        dealer.add(deck.draw(1))
        assertThat(dealer.isBust()).isTrue()
    }

    @Test
    fun `딜러는 블랙잭 여부를 반환할 수 있다`() {
        val deck = Deck(BlackjackCardMachine())
        val dealer = Dealer.withInitCards(deck)
        assertThat(dealer.isBlackjack()).isTrue()
    }

    @Test
    fun `딜러는 플레이어와의 게임에서 결과를 반환한다`() {
        val deck = Deck(BlackjackCardMachine())
        val players = Players.withInitCards(listOf("채채"), deck)
        val result = dealer.gameResult(players.gamePlayers)
        assertThat(result.values).containsAll(listOf(CompetitionResult.WIN))
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
    }
}
