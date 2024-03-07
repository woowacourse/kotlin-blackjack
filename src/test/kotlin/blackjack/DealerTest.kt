package blackjack

import blackjack.model.deck.CardMachineManager
import blackjack.model.CompetitionResult
import blackjack.model.participant.Dealer
import blackjack.model.deck.Deck
import blackjack.model.participant.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        CardMachineManager.machine = TestCardMachine()
        deck = Deck()
        dealer = Dealer(deck)
    }

    @Test
    fun `딜러는 게임이 시작될 때 카드 2장을 받는다`() {
        assertThat(dealer.getAllCards().split(", ").size).isEqualTo(INIT_CARD_SIZE)
    }

    @Test
    fun `딜러 카드 합은 16이하이므로 카드 한장을 더 받을 수 있다`() {
        assertThat(dealer.addCard()).isTrue
    }

    @Test
    fun `딜러는 카드를 추가로 받을 수 있다`() {
        dealer.addCard()
        assertThat(dealer.getAllCards().split(", ").size).isEqualTo(3)
    }

    @Test
    fun `딜러는 플레이어와의 게임에서 결과를 반환한다`() {
        val players = Players.playerNamesOf(listOf("채채"), deck)
        val result = dealer.gameResult(players.gamePlayers)
        assertThat(result.values).containsAll(listOf(CompetitionResult.WIN))
    }

    companion object {
        private const val INIT_CARD_SIZE = 2
    }
}