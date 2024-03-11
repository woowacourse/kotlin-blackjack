package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.GameState
import blackjack.model.Participant.Dealer
import blackjack.view.OutputView
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
    fun `카드 추가로 뽑기`() {
        dealer.draw(Card(CardNumber.TWO, CardSymbol.SPADE))
        dealer.draw(Card(CardNumber.THREE, CardSymbol.SPADE))
        dealer.additionalDraw(cardDeck) { OutputView.outputDealerDraw(dealer) }

        assertThat(dealer.gameInformation.cards.size >= 3).isTrue
        assertThat(dealer.gameInformation.state != GameState.Running.HIT).isTrue
    }
}
