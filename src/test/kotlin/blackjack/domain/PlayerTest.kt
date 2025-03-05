package blackjack.domain

import blackjack.domain.enums.Rank
import blackjack.domain.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 카드를 한 장 지급 받으면 플레이어의 패는 한 장이다`() {
        // given
        val hand = Hand(emptyList())
        val player = Player("Jason", hand)
        val card = Card(Rank.ACE, Suit.SPADE)

        // when
        player.addCard(card)

        // then
        assertThat(player.hand.cards.size).isEqualTo(1)
    }

    @Test
    fun `플레이어가 Ace 한 장과 Queen 한 장을 가지면 점수는 21이다`() {
        // given
        val aceCard = Card(Rank.ACE, Suit.SPADE)
        val queenCard = Card(Rank.QUEEN, Suit.SPADE)
        val hand = Hand(listOf(aceCard, queenCard))
        val player = Player("Jason", hand)

        // when
        val score = player.calculateScore()

        // then
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `플레이어가 Ace 두 장과 9 한 장을 가지면 점수는 21이다`() {
        // given
        val aceSpade = Card(Rank.ACE, Suit.SPADE)
        val aceDiamond = Card(Rank.ACE, Suit.DIAMOND)
        val nineSpade = Card(Rank.NINE, Suit.SPADE)
        val hand = Hand(listOf(aceSpade, aceDiamond, nineSpade))
        val player = Player("Jason", hand)

        // when
        val score = player.calculateScore()

        // then
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `플레이어가 Queen 세 장을 가지면 버스트이다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val queenDiamond = Card(Rank.QUEEN, Suit.DIAMOND)
        val hand = Hand(listOf(queenSpade, queenHeart, queenDiamond))
        val player = Player("Jason", hand)

        // when
        val isBust = player.isBust()

        // then
        assertThat(isBust).isTrue()
    }

    @Test
    fun `플레이어가 Queen 두 장을 가지면 버스트가 아니다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val hand = Hand(listOf(queenSpade, queenHeart))
        val player = Player("Jason", hand)

        // when
        val isBust = player.isBust()

        // then
        assertThat(isBust).isFalse()
    }
}
