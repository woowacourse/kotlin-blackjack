package blackjack.fixture

import blackjack.model.card.Card
import blackjack.model.participant.Dealer

fun createRunningDealer(vararg cards: Card): Dealer = Dealer(createRunningState(*cards))

fun createBustDealer(): Dealer = Dealer(createBustState())

fun Dealer.play(onDraw: () -> Card) = play(onDraw, {})
