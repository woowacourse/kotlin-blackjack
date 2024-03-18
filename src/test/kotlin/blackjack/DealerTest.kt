package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import blackjack.model.user.Participant.Dealer
import blackjack.view.ProgressOutputView
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

        assertThat(dealer.hand.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러 카드 자동으로 추가로 뽑을때 손패 추가 확인`() {
        dealer.draw(Card(CardNumber.TWO, CardSymbol.SPADE))
        dealer.draw(Card(CardNumber.THREE, CardSymbol.SPADE))
        dealer.judgeDrawOrNot(cardDeck) { ProgressOutputView.outputDealerDraw(dealer) }

        assertThat(dealer.hand.cards.size >= 3).isTrue
    }
}
