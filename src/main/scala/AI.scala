package rps

class AI() extends Player() {

  override def choose(): String = {
    val newChoice = choosePreferred()
    lastChoice = newChoice
    newChoice
  }

  def choosePreferred(): String = {
    lastHand match {
      case 0 => chooseWithBias()
      case 1 => chooseWithBias(RPS.losesTo(lastChoice), 3)
      case 2 => chooseWithBias(RPS.beats(lastChoice), 3)
      case _ => chooseWithBias()
    }
  }

  def chooseWithBias(biasTowards: String = "", biasVal: Int = 0): String = {
    val rand = scala.util.Random
    val randInt = rand.nextInt(3 + biasVal)
    if (biasTowards.isEmpty || randInt < 3)
      RPS.rpsChoiceFromVal(randInt)
    else
      biasTowards
  }

}