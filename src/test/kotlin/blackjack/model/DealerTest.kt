package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player
    private val dealingShoe = DealingShoe(listOf(FOUR_CARD, TWO_CARD))

    @BeforeEach
    fun `모든 테스트에 딜러와 플레이어 한 명이 참가하며, 딜러는 3하트 카드를 갖도록 세팅한다`() {
        player = Player("빙티")
        dealer = creatDealer(THREE_CARD)
    }

    @Test
    fun `딜러가 초기 카드를 2장 가져와 3장을 가진다`() {
        dealer initFrom dealingShoe
        val actual = dealer.showCard()
        assertThat(actual.size).isEqualTo(3)
        assertThat(actual).isEqualTo(listOf(THREE_CARD, FOUR_CARD, TWO_CARD))
    }

    @Test
    fun `딜러가 딜링슈에서 맨 앞의 2하트 카드를 한 개 뽑아 가져온다`() {
        dealer hitFrom dealingShoe
        val actual = dealer.showCard()
        assertThat(actual).isEqualTo(listOf(THREE_CARD, FOUR_CARD))
    }

    @Test
    fun `플레이어만 버스트되면 LOSE를 반환한다`() {
        player.addCard(TEN_CARD)
        player.addCard(TEN_CARD)
        player.addCard(TEN_CARD)

        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `모두 버스트되면 LOSE를 반환한다`() {
        player.addCard(TEN_CARD)
        player.addCard(TEN_CARD)
        player.addCard(TEN_CARD)

        dealer.addCard(TEN_CARD)
        dealer.addCard(TEN_CARD)
        dealer.addCard(TEN_CARD)

        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러만 버스트되면 WIN을 반환한다`() {
        player.addCard(TWO_CARD)

        dealer.addCard(TEN_CARD)
        dealer.addCard(TEN_CARD)
        dealer.addCard(TEN_CARD)

        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 크면 LOSE를 반환한다`() {
        player.addCard(TWO_CARD)
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합과 같으면 DRAW를 반환한다`() {
        player.addCard(Card(CardNumber.THREE, Suit.HEART))
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 작으면 WIN을 반환한다`() {
        player.addCard(FOUR_CARD)
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러의 카드 총 합이 16 이하면 true를 반환한다`() {
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `딜러의 카드 총 합이 17 이상이면 false를 반환한다`() {
        dealer.addCard(TEN_CARD)
        dealer.addCard(SEVEN_CARD)
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(false)
    }

    private fun creatDealer(vararg cards: Card): Dealer {
        val dealer = Dealer()
        cards.forEach {
            dealer.addCard(it)
        }
        return dealer
    }

    companion object {
        val TWO_CARD = Card(CardNumber.TWO, Suit.HEART)
        val THREE_CARD = Card(CardNumber.THREE, Suit.HEART)
        val FOUR_CARD = Card(CardNumber.FOUR, Suit.HEART)
        val SEVEN_CARD = Card(CardNumber.SEVEN, Suit.HEART)
        val TEN_CARD = Card(CardNumber.TEN, Suit.HEART)
    }
}
