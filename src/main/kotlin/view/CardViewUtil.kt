package view

import domain.card.Card

fun Card.toCardInfo(): String = "${this.cardNumber.cardSign}${this.shape.pattern}"
