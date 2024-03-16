package model.participants

data class Wallet(val participantName: ParticipantName = ParticipantName.fromInput("None"), val money: Money = Money())
