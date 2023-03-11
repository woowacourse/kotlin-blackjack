package study

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface State {

    fun profit(bet: Double): Double

    fun stay(): State
}

abstract class Start(val cards: Cards) : State {
    abstract fun draw(card: Card): State

    abstract override fun stay(): State

    abstract val dividend: Double

    override fun profit(bet: Double): Double = bet * dividend
}

class Hit(cards: Cards) : Start(cards) {
    override val dividend: Double
        get() = 1.0

    override fun draw(card: Card): State {
        cards.add(card)
        return when {
            cards.sum() > 21 -> Bust()
            cards.sum() == 21 -> Blackjack()
            else -> Hit(cards)
        }
    }

    override fun stay() = Stay()
}

abstract class End() : State {

    abstract val dividend: Double

    override fun stay() = Stay()

    override fun profit(bet: Double): Double = bet * dividend
}

class Bust(override val dividend: Double = -1.0) : End()

class Stay(override val dividend: Double = 0.0) : End()

class Blackjack(override val dividend: Double = 1.5) : End()

class Cards(private val cards: MutableList<Card>) {

    fun calculateSum(): Int = cards.sumOf { it.number.value }

    fun add(card: Card) = cards.add(card)
}

class Study {
    @Test
    fun hit() {
        val cards = Cards(
            Pair(CardNumber.TWO, CardShape.DIAMOND),
            Pair(CardNumber.THREE, CardShape.DIAMOND)
        )
        val hit = Hit(cards).draw(Card.from(CardNumber.FOUR, CardShape.DIAMOND))
        assertThat(hit).isInstanceOf(Hit::class.java)
    }

    @Test
    fun bust() {
        val cards = Cards(
            Pair(CardNumber.TEN, CardShape.DIAMOND),
            Pair(CardNumber.NINE, CardShape.DIAMOND)
        )
        val actual = Hit(cards).draw(Card.from(CardNumber.TEN, CardShape.DIAMOND))
        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun blackjack() {
        val cards = Cards(
            Pair(CardNumber.TEN, CardShape.DIAMOND),
            Pair(CardNumber.FIVE, CardShape.DIAMOND)
        )
        val actual = Hit(cards).draw(Card.from(CardNumber.SIX, CardShape.DIAMOND))
        assertThat(actual).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun stay() {
        val cards = Cards(
            Pair(CardNumber.THREE, CardShape.DIAMOND),
            Pair(CardNumber.TWO, CardShape.DIAMOND)
        )
        val actual = Hit(cards).stay()
        assertThat(actual).isInstanceOf(Stay::class.java)
    }

    @Test
    fun hit_stay() {
        val cards = Cards(
            Pair(CardNumber.TWO, CardShape.DIAMOND),
            Pair(CardNumber.THREE, CardShape.DIAMOND)
        )

        // val hit = Hit(cards).draw(Card(CardNumber.FOUR, CardShape.DIAMOND)).stay()
        val hit = Hit(cards).draw(Card.from(CardNumber.FOUR, CardShape.DIAMOND))
        assertThat(hit).isInstanceOf(Hit::class.java)
    }
}

/*
class 첫턴
첫턴일 때 드로우를 한다.
드로우를 한 후에 카드 숫자가 2개가 되면 힛 또는 블랙잭을 반환한다
러닝...
*/

// class HitTest {
//    @Test
//    fun bust() {
//        val hit = Hit(CLUBS_TWO, CLUBS_TEN)
//        val actual = hit.draw(CLUBS_KING)
//        assertThat(actual).isInstanceOf(Bust::class.java)
//    }
//
//    @Test
//    fun hit() {
//        val hit = Hit(CLUBS_TWO, CLUBS_THREE)
//        val actual = hit.draw(CLUBS_KING)
//        assertThat(actual).isInstanceOf(Hit::class.java)
//    }
//
//    @Test
//    fun hit_and_hit() {
//        val hit = Hit(CLUBS_TWO, CLUBS_THREE)
//        val actual = hit.draw(CLUBS_KING).draw(CLUBS_FIVE)
//        assertThat(actual).isInstanceOf(Hit::class.java)
//    }
//
//    @Test
//    fun hit_and_bust() {
//        val hit = Hit(CLUBS_TWO, CLUBS_THREE)
//        val actual = hit.draw(CLUBS_KING).draw(CLUBS_TEN)
//        assertThat(actual).isInstanceOf(Bust::class.java)
//    }
//
//    @Test
//    fun stay() {
//        val hit = Hit(CLUBS_TWO, CLUBS_THREE)
//        val actual = hit.stay()
//        assertThat(actual).isInstanceOf(Stay::class.java)
//    }
// }
