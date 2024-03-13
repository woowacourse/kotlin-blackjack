package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealingShoeTest {
    private val twoHeartCard = Card(CardNumber.TWO, Suit.HEART)
    private val threeHeartCard = Card(CardNumber.THREE, Suit.HEART)
    private val fourHeartCard = Card(CardNumber.FOUR, Suit.HEART)
    private val fiveHeartCard = Card(CardNumber.FIVE, Suit.HEART)
    private lateinit var dealer: Dealer
    private lateinit var player: Player
    private lateinit var dealingShoe: DealingShoe

    @Test
    fun `2하트가 남아 있을 때, draw 시 2하트가 나와야 한다`() {
        val dealingShoeWithTwoHeart = DealingShoe(listOf(twoHeartCard))
        dealingShoeWithTwoHeart giveCardTo player
        val actual = player.showCard()
        assertThat(actual).isEqualTo(listOf(twoHeartCard))
    }

    @BeforeEach
    fun `딜러와 빙티, 4장의 카드로 테스트를 진행한다`() {
        dealer = Dealer()
        player = Player("빙티")
        dealingShoe = DealingShoe(listOf(twoHeartCard, threeHeartCard, fourHeartCard, fiveHeartCard))
    }

    @Test
    fun `각 참가자들에게 초기 카드를 2장씩 나눠준다`() {
        dealingShoe.initGame(dealer, listOf(player))
        val dealerCardSize = dealer.showCard().size
        val playerCardSize = player.showCard().size
        assertThat(dealerCardSize).isEqualTo(2)
        assertThat(playerCardSize).isEqualTo(2)
    }

    @Test
    fun `맨 앞의 카드를 한 개 뽑아 플레이어에게 나눠준다`() {
        dealingShoe giveCardTo player
        val actual = player.showCard()
        assertThat(actual).isEqualTo(listOf(twoHeartCard))
    }

    @Test
    fun `맨 앞의 카드를 한 개 뽑아 딜러에게 나눠준다`() {
        dealingShoe giveCardTo dealer
        val actual = dealer.showCard()
        assertThat(actual).isEqualTo(listOf(twoHeartCard))
    }

    @Test
    fun `4장의 카드가 있을 때, 카드를 5번 뽑으면 예외를 던진다`() {
        val cardSize = 4
        val exception =
            assertThrows<IllegalStateException> {
                repeat(cardSize + 1) {
                    dealingShoe giveCardTo player
                }
            }.message
        assertThat(exception).isEqualTo("모든 카드를 소진해 게임을 종료합니다.")
    }
}
