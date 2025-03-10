package model

import view.CardRankMessage.getCardRankMessage
import view.ShapeMessage.getShapeMessage

fun Card.displayName(): String {
    return getCardRankMessage(this.cardRank) + getShapeMessage(this.shape)
}

fun List<Card>.displayNames(): List<String> {
    return this.map { it.displayName() }
}
