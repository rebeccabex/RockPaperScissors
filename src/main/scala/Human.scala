package rps

class Human() extends Player() {

  override def choose(): String = {
    readInput("Enter choice, or 'quit' to exit: ")
  }

  def readInput(prompt: String): String = {
    print(prompt)
    scala.io.StdIn.readLine()
  }


}
