package blackjack.domain

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.ScoreCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var cardDeck: CardDeck
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        cardDeck = CardDeck()
        dealer = Dealer(ScoreCalculator())
    }

    @Test
    fun `딜러가 카드를 다 뽑고나면 점수는 16점을 초과하거나 버스트이다`() {
        // given & when
        dealer.drawUntilFinished(cardDeck)

        // then
        assertThat(dealer.score() > 16 || dealer.isBust()).isTrue()
    }

    @Test
    fun `딜러 점수가 16 이전까지 뽑은 카드의 장수를 반환한다`() {
        // given
        val initialDrawCount = 2
        dealer.draw(cardDeck)

        // when
        val dealerDrawCount = dealer.drawUntilFinished(cardDeck)

        // then
        assertThat(dealerDrawCount).isEqualTo(dealer.cards.size - initialDrawCount)
    }
}
