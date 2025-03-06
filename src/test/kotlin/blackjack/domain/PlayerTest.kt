package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Result
import blackjack.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player("Jason")
        dealer = Dealer()
    }

    @Test
    fun `플레이어가 카드를 한 장 지급 받으면 플레이어의 패는 한 장이다`() {
        // given
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
        val eightSpade = Card(Rank.EIGHT, Suit.SPADE)

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        dealer.addCard(queenSpade)
        dealer.addCard(eightSpade)
        val result = player.getResult(dealer)

        // then
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 21이면 플레이어가 진다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val aceSpade = Card(Rank.ACE, Suit.SPADE)

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        dealer.addCard(queenSpade)
        dealer.addCard(aceSpade)
        val result = player.getResult(dealer)

        // then
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 22이면 플레이어가 이긴다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val twoSpade = Card(Rank.TWO, Suit.SPADE)

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        dealer.addCard(queenSpade)
        dealer.addCard(queenHeart)
        dealer.addCard(twoSpade)
        val result = player.getResult(dealer)

        // then
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `플레이어의 점수가 20이고 딜러의 점수가 20이면 긴다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)

        // when
        player.addCard(queenSpade)
        player.addCard(queenHeart)
        dealer.addCard(queenSpade)
        dealer.addCard(queenHeart)
        val result = player.getResult(dealer)

        // then
        assertThat(result).isEqualTo(Result.DRAW)
    }
}
