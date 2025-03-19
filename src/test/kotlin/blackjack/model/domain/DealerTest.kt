package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private val dealer = Dealer()

    @BeforeEach
    fun setup() {
        // given
        dealer.receiveCard(listOf(Card.from("AceHeart"), Card.from("FiveSpade")))
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        // when
        val actual = dealer.cardDeck
        val expected = listOf(Card.from("AceHeart"), Card.from("FiveSpade"))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러 카드 숫자 합이 16보다 작거나 같으면 카드를 받아올 수 있도록 True를 반환한다`() {
        // when
        val actual = dealer.canHit()
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `딜러 카드 숫자 합이 16보다 크면 카드를 받지 않도록 False를 반환한다`() {
        // given
        dealer.receiveCard(listOf(Card.from("QueenHeart"), Card.from("TwoSpade")))
        // when
        val actual = dealer.canHit()
        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `딜러는 처음으로 카드 보여줄 때 항상 1장으로 출력된다`() {
        // when
        val actual = dealer.showStartCards()
        val expected = listOf(Card.from("AceHeart"))
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
