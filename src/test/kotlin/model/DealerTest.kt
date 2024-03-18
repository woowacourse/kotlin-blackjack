package model

import TestDeck
import fixture.SPADE_ACE
import fixture.SPADE_FIVE
import fixture.SPADE_FOUR
import fixture.SPADE_KING
import fixture.SPADE_TEN
import fixture.SPADE_THREE
import model.human.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var hand: Hand

    private var testDeck =
        TestDeck(
            mutableListOf(
                SPADE_TEN,
                SPADE_FOUR,
                SPADE_FIVE,
            ),
        )

    @BeforeEach
    fun handInit() {
        hand = Hand()
    }

    @Test
    fun `play() 가 끝나면 핸드의 합은 17 이상이다`() {
        val dealer = Dealer(Hand())
        dealer.play(testDeck)

        assertThat(dealer.hand.getPoint().amount >= 17).isTrue
    }

    @Test
    fun `핸드의 합이 21 초과인 경우 버스트가 된다`() {
        hand.add(SPADE_TEN)
        hand.add(SPADE_KING)
        hand.add(SPADE_THREE)

        val dealer = Dealer(hand)

        assertThat(dealer.isBusted()).isTrue
    }

    @Test
    fun `블랙잭 판단을 할 수 있다`() {
        hand.add(SPADE_TEN)
        hand.add(SPADE_ACE)

        val dealer = Dealer(hand)
        assertThat(dealer.isBlackJack()).isTrue
    }
}
