package cats.bench

import cats.Foldable
import cats.data.Chain
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}

@State(Scope.Thread)
class ChainCollectFirstBench {

  private val foldableChain = Foldable[Chain]

  private val small = Chain(1, 2, 3, 4, 5)
  private val smallLast = 5

  private val medium = (0 to 10)
    .foldLeft(Chain.empty[Int])((acc, _) => acc ++ Chain.fromSeq(0 to 10))
  private val mediumLast = 10 * 10

  private val large = (0 to 1000)
    .foldLeft(Chain.empty[Int])((acc, _) => acc ++ Chain.fromSeq(0 to 1000))
  private val largeLast = 1000 * 1000

  @Benchmark
  def collectFirstSmallNew: Option[Int] = small.collectFirst {
    case x if x == smallLast => x
  }

  @Benchmark
  def collectFirstSmallFoldable: Option[Int] = foldableChain.collectFirst(small) {
    case x if x == smallLast => x
  }

  @Benchmark
  def collectFirstMediumNew: Option[Int] = medium.collectFirst {
    case x if x == mediumLast => x
  }

  @Benchmark
  def collectFirstMediumFoldable: Option[Int] = foldableChain.collectFirst(medium) {
    case x if x == mediumLast => x
  }

  @Benchmark
  def collectFirstLargeNew: Option[Int] = medium.collectFirst {
    case x if x == largeLast => x
  }

  @Benchmark
  def collectFirstLargeFoldable: Option[Int] = foldableChain.collectFirst(medium) {
    case x if x == largeLast => x
  }



  @Benchmark
  def collectFirstSomeSmallNew: Option[Int] = small.collectFirstSome {
    case x if x == smallLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSome2SmallNew: Option[Int] = small.collectFirstSome2 {
    case x if x == smallLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSomeSmallFoldable: Option[Int] = foldableChain.collectFirstSome(small) {
    case x if x == smallLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSomeMediumNew: Option[Int] = medium.collectFirstSome {
    case x if x == mediumLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSome2MediumNew: Option[Int] = medium.collectFirstSome2 {
    case x if x == mediumLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSomeMediumFoldable: Option[Int] = foldableChain.collectFirstSome(medium) {
    case x if x == mediumLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSomeLargeNew: Option[Int] = medium.collectFirstSome {
    case x if x == largeLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSome2LargeNew: Option[Int] = medium.collectFirstSome2 {
    case x if x == largeLast => Some(x)
    case _ => None
  }

  @Benchmark
  def collectFirstSomeLargeFoldable: Option[Int] = foldableChain.collectFirstSome(medium) {
    case x if x == largeLast => Some(x)
    case _ => None
  }
}
