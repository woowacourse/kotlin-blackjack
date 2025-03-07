package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드를 한 장 지급 받으면 딜러의 패는 한 장이다`() {
        // given
        val dealer = Dealer()
        val card = Card(Rank.ACE, Suit.SPADE)

        // when
        dealer.addCard(card)

        // then
        assertThat(dealer.hand.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러가 Ace 한 장과 Queen 한 장을 가지면 점수는 21이다`() {
        // given
        val aceCard = Card(Rank.ACE, Suit.SPADE)
        val queenCard = Card(Rank.QUEEN, Suit.SPADE)
        val dealer = Dealer()

        // when
        dealer.addCard(aceCard)
        dealer.addCard(queenCard)
        val score = dealer.calculateScore()

        // then
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `딜러가 Ace 두 장과 9 한 장을 가지면 점수는 21이다`() {
        // given
        val aceSpade = Card(Rank.ACE, Suit.SPADE)
        val aceDiamond = Card(Rank.ACE, Suit.DIAMOND)
        val nineSpade = Card(Rank.NINE, Suit.SPADE)
        val dealer = Dealer()

        // when
        dealer.addCard(aceSpade)
        dealer.addCard(aceDiamond)
        dealer.addCard(nineSpade)
        val score = dealer.calculateScore()

        // then
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `딜러가 Queen 세 장을 가지면 버스트이다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val queenDiamond = Card(Rank.QUEEN, Suit.DIAMOND)
        val dealer = Dealer()

        // when
        dealer.addCard(queenSpade)
        dealer.addCard(queenHeart)
        dealer.addCard(queenDiamond)
        val isBust = dealer.isBust()

        // then
        assertThat(isBust).isTrue()
    }

    @Test
    fun `딜러가 Queen 두 장을 가지면 버스트가 아니다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val dealer = Dealer()

        // when
        dealer.addCard(queenSpade)
        dealer.addCard(queenHeart)
        val isBust = dealer.isBust()

        // then
        assertThat(isBust).isFalse()
    }
}
