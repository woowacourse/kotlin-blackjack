package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Dealer {
    private val deck: Deck = Deck()

    fun giveCard(players: List<Player> = listOf()) {
        players.forEach { player ->
            player.getMoreCard(deck.getCard())
        }
    }
}

class Deck {
    private val aceCards: List<Card> =
        listOf(Card(Ace(), Suit.SPADE), Card(Ace(), Suit.HEART), Card(Ace(), Suit.DIAMOND), Card(Ace(), Suit.CLOVER))
    private val spadeNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.SPADE),
            Card(Number(3), Suit.SPADE),
            Card(Number(4), Suit.SPADE),
            Card(Number(5), Suit.SPADE),
            Card(Number(6), Suit.SPADE),
            Card(Number(7), Suit.SPADE),
            Card(Number(8), Suit.SPADE),
            Card(Number(9), Suit.SPADE),
            Card(Number(10), Suit.SPADE),
        )

    private val heartNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.HEART),
            Card(Number(3), Suit.HEART),
            Card(Number(4), Suit.HEART),
            Card(Number(5), Suit.HEART),
            Card(Number(6), Suit.HEART),
            Card(Number(7), Suit.HEART),
            Card(Number(8), Suit.HEART),
            Card(Number(9), Suit.HEART),
            Card(Number(10), Suit.HEART),
        )

    private val diamondNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.DIAMOND),
            Card(Number(3), Suit.DIAMOND),
            Card(Number(4), Suit.DIAMOND),
            Card(Number(5), Suit.DIAMOND),
            Card(Number(6), Suit.DIAMOND),
            Card(Number(7), Suit.DIAMOND),
            Card(Number(8), Suit.DIAMOND),
            Card(Number(9), Suit.DIAMOND),
            Card(Number(10), Suit.DIAMOND),
        )

    private val cloverNumberCards: List<Card> =
        listOf(
            Card(Number(2), Suit.CLOVER),
            Card(Number(3), Suit.CLOVER),
            Card(Number(4), Suit.CLOVER),
            Card(Number(5), Suit.CLOVER),
            Card(Number(6), Suit.CLOVER),
            Card(Number(7), Suit.CLOVER),
            Card(Number(8), Suit.CLOVER),
            Card(Number(9), Suit.CLOVER),
            Card(Number(10), Suit.CLOVER),
        )
    private val numberCards: List<Card> = spadeNumberCards + heartNumberCards + diamondNumberCards + cloverNumberCards

    private val spadeCharacterCards: List<Card> =
        listOf(Card(Character.JACK, Suit.SPADE), Card(Character.QUEEN, Suit.SPADE), Card(Character.KING, Suit.SPADE))

    private val heartCharacterCards: List<Card> =
        listOf(Card(Character.JACK, Suit.HEART), Card(Character.QUEEN, Suit.HEART), Card(Character.KING, Suit.HEART))

    private val diamondCharacterCards: List<Card> =
        listOf(
            Card(Character.JACK, Suit.DIAMOND),
            Card(Character.QUEEN, Suit.DIAMOND),
            Card(Character.KING, Suit.DIAMOND),
        )

    private val cloverCharacterCards: List<Card> =
        listOf(Card(Character.JACK, Suit.CLOVER), Card(Character.QUEEN, Suit.CLOVER), Card(Character.KING, Suit.CLOVER))

    private val characterCards =
        spadeCharacterCards + heartCharacterCards + diamondCharacterCards + cloverCharacterCards

    private val cards: MutableList<Card> = (aceCards + numberCards + characterCards).shuffled().toMutableList()

    fun getCard(): Card = cards.removeFirst()
}

class DealerTest {
    @Test
    fun `딜러는 플레이어에게 카드를 나눠준다`() {
        val dealer = Dealer()
        val eden = Player("Eden", Cards(emptyList()))
        val gio = Player("Gio", Cards(listOf(Card(Ace(), Suit.CLOVER), Card(Number(5), Suit.HEART))))
        val players: List<Player> = listOf(eden, gio)
        dealer.giveCard(players)
        assertThat(eden.getCountOfCards()).isEqualTo(1)
        assertThat(gio.getCountOfCards()).isEqualTo(3)
    }
}
