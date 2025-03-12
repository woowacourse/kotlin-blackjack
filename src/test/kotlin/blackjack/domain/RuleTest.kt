package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RuleTest {
    private var cards: List<Card> = listOf()
    private lateinit var player: Player
    private lateinit var dealer: Dealer
    private val queenCard = Card(Rank.QUEEN, Suit.HEART)
    private val aceCard = Card(Rank.ACE, Suit.HEART)
    private val twoCard = Card(Rank.TWO, Suit.HEART)

    @BeforeEach
    fun setUp() {
        cards = listOf()
        player = Player("a")
        dealer = Dealer()
    }

    @Test
    fun `Q 카드 한 장은 10점이다`() {
        // given
        cards = listOf(queenCard)
        val hand = Hand(cards)

        // when
        val score = Rule.calculateScore(hand)

        // then
        assertThat(score).isEqualTo(10)
    }

    @Test
    fun `A 카드를 포함하고 있을 때, 다른 카드가 10점 이하이면 보너스 점수 10점을 얻는다`() {
        // given
        cards = listOf(queenCard, aceCard)
        val hand = Hand(cards)

        // when
        val score = Rule.calculateScore(hand)

        // then
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `A 카드를 포함하고 있을 때, 다른 카드가 11점 이상이면 보너스 점수는 없다`() {
        // given
        cards = listOf(queenCard, aceCard, twoCard)
        val hand = Hand(cards)

        // when
        val score = Rule.calculateScore(hand)

        // then
        assertThat(score).isEqualTo(13)
    }

    @Test
    fun `플레이어의 카드 점수의 총 합이 21점을 초과하면 버스트이다`() {
        // given
        player.addCard(queenCard)
        player.addCard(aceCard)
        player.addCard(twoCard)
        player.addCard(queenCard)

        // when
        val actual = Rule.isBust(player.hand)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `플레이어의 카드 점수의 총 합이 21점 이하이면 버스트가 아니다`() {
        // given
        player.addCard(queenCard)
        player.addCard(aceCard)
        player.addCard(twoCard)

        // when
        val actual = Rule.isBust(player.hand)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어가 블랙잭이면 무승부다`() {
        // given
        player.addCard(queenCard)
        player.addCard(aceCard)
        player.addCard(twoCard)

        // when
        val actual = Rule.isBust(player.hand)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어가 블랙잭이 아니면 플레이어가 진다`() {
        // given
        dealer.addCard(queenCard)
        dealer.addCard(aceCard)

        player.addCard(aceCard)
        player.addCard(aceCard)
        player.addCard(aceCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 플레이어가 이긴다`() {
        // given
        dealer.addCard(aceCard)
        dealer.addCard(aceCard)
        dealer.addCard(aceCard)

        player.addCard(queenCard)
        player.addCard(aceCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.WIN)
    }

    @Test
    fun `플레이어와 딜러가 둘 다 버스트이면 플레이어가 진다`() {
        // given
        dealer.addCard(queenCard)
        dealer.addCard(queenCard)
        dealer.addCard(queenCard)

        player.addCard(queenCard)
        player.addCard(queenCard)
        player.addCard(queenCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.LOSE)
    }

    @Test
    fun `플레이어만 버스트이면 플레이어가 진다`() {
        // given
        dealer.addCard(queenCard)
        dealer.addCard(queenCard)

        player.addCard(queenCard)
        player.addCard(queenCard)
        player.addCard(queenCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러만 버스트이면 플레이어가 이긴다`() {
        // given
        dealer.addCard(queenCard)
        dealer.addCard(queenCard)
        dealer.addCard(queenCard)

        player.addCard(queenCard)
        player.addCard(queenCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.WIN)
    }

    @Test
    fun `플레이어 점수보다 딜러 점수가 더 크면 플레이어가 진다`() {
        // given
        dealer.addCard(queenCard)
        dealer.addCard(aceCard)

        player.addCard(queenCard)
        player.addCard(queenCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러 점수보다 플레이어 점수가 더 크면 플레이어가 이긴다`() {
        // given
        dealer.addCard(queenCard)
        dealer.addCard(queenCard)

        player.addCard(queenCard)
        player.addCard(aceCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러 점수와 플레이어 점수가 같으면 무승부이다`() {
        // given
        dealer.addCard(queenCard)
        dealer.addCard(queenCard)

        player.addCard(queenCard)
        player.addCard(queenCard)

        // when
        val actual = Rule.getPlayerResult(dealer, player)

        // then
        assertThat(actual).isEqualTo(Result.PUSH)
    }
}
