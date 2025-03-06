package blackjack.domain

import blackjack.domain.enums.Rank
import blackjack.domain.enums.Result
import blackjack.domain.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 카드를 한 장 지급 받으면 플레이어의 패는 한 장이다`() {
        // given
        val player = Player("Jason")
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
        val player = Player("Jason")

        // when
        player.addCard(aceCard)
        player.addCard(queenCard)
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
        val player = Player("Jason")

        // when
        player.addCard(aceSpade)
        player.addCard(aceDiamond)
        player.addCard(nineSpade)
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
        val player = Player("Jason")

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        player.addCard(queenDiamond)
        val isBust = player.isBust()

        // then
        assertThat(isBust).isTrue()
    }

    @Test
    fun `플레이어가 Queen 두 장을 가지면 버스트가 아니다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val player = Player("Jason")

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        val isBust = player.isBust()

        // then
        assertThat(isBust).isFalse()
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 18이면 플레이어가 이긴다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val player = Player("Jason")

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        val result = player.getResult(18)

        // then
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 21이면 플레이어가 진다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val player = Player("Jason")

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        val result = player.getResult(21)

        // then
        assertThat(result).isEqualTo(Result.LOSE)
    }
}
