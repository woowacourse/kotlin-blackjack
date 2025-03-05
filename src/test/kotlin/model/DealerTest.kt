package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 게임을 시작하면 2장의 카드를 갖는다`() {
        val ace = Card(CardRank.ACE, Shape.CLUB)
        val six = Card(CardRank.SIX, Shape.CLUB)

        assertThat(Cards(mutableListOf(ace, six)).allCards.size).isEqualTo(2)
    }

    @Test
    fun `딜러는 처음에 1장의 카드를 오픈할 수 있다`() {
        val card1 = Card(CardRank.ACE, Shape.CLUB)
        val card2 = Card(CardRank.SIX, Shape.SPADE)

        val dealer = Dealer(Cards(mutableListOf(card1, card2)))
        val initialShowCount = 1
        val showDealerCard = dealer.cards.showInitialCards(initialShowCount)

        assertThat(showDealerCard).isEqualTo(card1)
    }
}
