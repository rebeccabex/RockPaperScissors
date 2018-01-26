package rps

class AI() extends Player() {

  override def choose(): String = {
    var newChoice = ""
    lastHand match {
      case 0 => newChoice = chooseWithBias()
      case 1 => newChoice = chooseWithBias(RPS.losesTo(lastChoice), 3)
      case 2 => newChoice = chooseWithBias(RPS.beats(lastChoice), 3)
      case _ => newChoice = chooseWithBias()
    }
    lastChoice = newChoice
    newChoice
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