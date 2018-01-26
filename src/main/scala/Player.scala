package rps

abstract class Player() {
  var wins = 0
  var lastChoice = ""
  var lastHand = 0

  def result(result: Int): Unit = {
    lastHand = result
  }

  def won(): Unit = {
    lastHand = 1
    wins += 1
  }

  def drew(): Unit = {
    lastHand = 0
  }

  def lost(): Unit = {
    lastHand = 2
  }

  def choose(): String

}
