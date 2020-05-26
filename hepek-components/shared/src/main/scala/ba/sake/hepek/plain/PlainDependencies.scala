package ba.sake.hepek.plain

import ba.sake.hepek.html._
import ba.sake.hepek.jquery.JQueryDependencies

// stolen from https://hacks.mozilla.org/2017/04/replace-bootstrap-layouts-with-css-grid/
trait PlainDependencies extends JQueryDependencies {

  private val gridStyles = {
    val Total = 12

    def cs(size: String, colSpan: Int) =
      s".col-$size-$colSpan { grid-column: span $colSpan }"

    val xs = (1 to Total).map(colSpan => cs("xs", colSpan)).mkString("\n")
    val sm = (1 to Total).map(colSpan => cs("sm", colSpan)).mkString("\n")
    val md = (1 to Total).map(colSpan => cs("md", colSpan)).mkString("\n")
    val lg = (1 to Total).map(colSpan => cs("lg", colSpan)).mkString("\n")

    s"""
      .row {
        display: grid;
        grid-template-columns: repeat(12, 1fr);
        grid-gap: 20px;
      }
      $xs
      @media (min-width: 768px) { $sm }
      @media (min-width: 992px) { $md }
      @media (min-width: 1200px) { $lg }
    """
  }

  override def stylesInline =
    super.stylesInline ++ List(gridStyles)
}
