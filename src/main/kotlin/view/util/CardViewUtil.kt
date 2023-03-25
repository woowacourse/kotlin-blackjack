package view.util

import model.domain.card.Card

fun Card.toCardInfo(): String = "${this.cardNumber.cardSign}${this.shape.pattern}"
