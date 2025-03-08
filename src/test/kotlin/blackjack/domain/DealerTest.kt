package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Result
import blackjack.enums.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러가 카드를 한 장 지급 받으면 딜러의 패는 한 장이다`() {
        // given
        val card = Card(Rank.ACE, Suit.SPADE)

        // when
        dealer.drawCard(card)

        // then
        assertThat(dealer.hand.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러가 Ace 한 장과 Queen 한 장을 가지면 점수는 21이다`() {
        // given
        val aceCard = Card(Rank.ACE, Suit.SPADE)
        val queenCard = Card(Rank.QUEEN, Suit.SPADE)

        // when
        dealer.drawCard(aceCard)
        dealer.drawCard(queenCard)
        val score = dealer.getScore()

        // then
        assertThat(score.score).isEqualTo(21)
    }

    @Test
    fun `딜러가 Ace 두 장과 9 한 장을 가지면 점수는 21이다`() {
        // given
        val aceSpade = Card(Rank.ACE, Suit.SPADE)
        val aceDiamond = Card(Rank.ACE, Suit.DIAMOND)
        val nineSpade = Card(Rank.NINE, Suit.SPADE)

        // when
        dealer.drawCard(aceSpade)
        dealer.drawCard(aceDiamond)
        dealer.drawCard(nineSpade)
        val score = dealer.getScore()

        // then
        assertThat(score.score).isEqualTo(21)
    }

    @Test
    fun `딜러 점수가 16이면 카드를 더 뽑을 수 있다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val sixSpade = Card(Rank.SIX, Suit.SPADE)

        // when
        dealer.drawCards(queenSpade, sixSpade)

        // then
        assertThat(dealer.canHit()).isTrue()
    }

    @Test
    fun `딜러 점수가 17이면 카드를 더 뽑을 수 없다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val sevenSpade = Card(Rank.SEVEN, Suit.SPADE)

        // when
        dealer.drawCards(queenSpade, sevenSpade)

        // then
        assertThat(dealer.canHit()).isFalse()
    }

    @Test
    fun `딜러의 점수가 21이고 상대 점수가 20이면 딜러가 이긴다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val aceSpade = Card(Rank.ACE, Suit.SPADE)

        // when
        dealer.drawCards(queenSpade, aceSpade)
        val result = dealer.getResult(Score(20))

        // then
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러의 점수가 18이고 상대 점수가 20이면 딜러가 진다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val eightSpade = Card(Rank.EIGHT, Suit.SPADE)

        // when
        dealer.drawCards(queenSpade, eightSpade)
        val result = dealer.getResult(Score(20))

        // then
        assertThat(result).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러의 점수가 22이고 상대 점수가 22이면 딜러가 이긴다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)
        val twoSpade = Card(Rank.TWO, Suit.SPADE)

        // when
        dealer.drawCards(queenSpade, queenHeart, twoSpade)
        val result = dealer.getResult(Score(22))

        // then
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러의 점수가 20이고 상대 점수가 20이면 비긴다`() {
        // given
        val queenSpade = Card(Rank.QUEEN, Suit.SPADE)
        val queenHeart = Card(Rank.QUEEN, Suit.HEART)

        // when
        dealer.drawCards(queenSpade, queenHeart)
        val result = dealer.getResult(Score(20))

        // then
        assertThat(result).isEqualTo(Result.PUSH)
    }

    private fun Participant.drawCards(vararg cards: Card) {
        cards.forEach { this.drawCard(it) }
    }
}
