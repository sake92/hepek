package ba.sake.hepek.matchers
import org.scalatest.matchers.{MatchResult, Matcher}

trait HepekMatchers {

  /**
    * Returns a matcher to assert if a pair representing major and minor version is equal or greater than
    * the major and minor parameters provided.
    * @param major - minimal major version accepted
    * @param minor - minimal minor version accepted
    * @return a Matcher
    */
  def beEqualOrGreaterThanVersion(major: Int, minor: Int) =
    Matcher { left: (Int, Int) =>
      MatchResult(
        left._1 > major || (left._1 == major && left._2 >= minor),
        s"$left +  version is not valid because not equal or greater than $major.$minor",
        s"$left +  version is valid because equal or greater than $major.$minor"
      )
    }
}
