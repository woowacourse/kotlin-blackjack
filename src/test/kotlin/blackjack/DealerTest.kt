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
    fun `카드 한장 뽑아서 딜러의 손패에 추가하기`() {
        dealer.draw(cardDeck.pickCard())

        assertThat(dealer.gameInformation.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러 카드 자동으로 추가로 뽑을때 손패 및 상태 변경 확인`() {
        dealer.draw(Card(CardNumber.TWO, CardSymbol.SPADE))
        dealer.draw(Card(CardNumber.THREE, CardSymbol.SPADE))
        dealer.judgeDrawOrNot(cardDeck) { OutputView.outputDealerDraw(dealer) }

        assertThat(dealer.gameInformation.cards.size >= 3).isTrue
        assertThat(dealer.gameInformation.state != GameState.Running.HIT).isTrue
    }
}
