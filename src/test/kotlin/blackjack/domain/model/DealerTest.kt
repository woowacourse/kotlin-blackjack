package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun `setUp`() {
        dealer = Dealer("동전", cards = listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `딜러는 이름을 가진다`() {
        assertThat(dealer.name).isEqualTo("동전")
    }

    @Test
    fun `딜러는 카드를 가진다`() {
        assertThat(dealer.cards).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `딜러는 카드를 받는다`() {
        assertThat(dealer.accept(Card(Suit.HEART, Rank.KING))).isEqualTo(
            dealer.copy(
                cards =
                    listOf(
                        Card(
                            Suit.HEART,
                            Rank.ACE,
                        ),
                        Card(Suit.HEART, Rank.KING),
                    ),
            ),
        )
    }

    @Test
    fun `카드의 보너스 점수를 추가한 총합을 반환한다`() {
        dealer = Dealer("동전", cards = listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)))
        assertThat(dealer.getScore()).isEqualTo(21)
    }

    @Test
    fun `카드의 보너스 점수가 없는 총합을 반환한다`() {
        dealer =
            Dealer(
                "동전",
                cards = listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.SPADE, Rank.KING)),
            )
        assertThat(dealer.getScore()).isEqualTo(21)
    }
}
