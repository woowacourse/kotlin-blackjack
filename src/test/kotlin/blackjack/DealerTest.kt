package blackjack

import blackjack.model.CardDeck
import blackjack.model.GameState
import blackjack.model.Participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        cardDeck = CardDeck()
    }

    @Test
    fun `카드 한장 뽑기`() {
        dealer.draw(cardDeck.pickCard())

        assertThat(dealer.gameInformation.cards.size).isEqualTo(1)
    }

    @Test
    fun `상태 HIT로 전환`() {
        dealer.changeStateToHit()

        assertThat(dealer.gameInformation.state).isEqualTo(GameState.Running.HIT)
    }
}
