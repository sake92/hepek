package ba.sake.hepek.bulma.statik

import ba.sake.hepek.bulma.component._
import ba.sake.hepek.bulma.grid.BulmaGridComponents
import ba.sake.hepek.bulma.{Centered, EmptyAttribute, PrimaryColor}
import scalatags.Text
import scalatags.Text.all._

object MockBulmaPage extends BulmaStaticPage with BulmaGridComponents {

  def contentData =
    CardContent(
      div(p("hello")),
      ul(
        li("one"),
        li("two")
      )
    )

  val navBar = NavbarComponents.navbar(
    PlainAnchorNavbarItem("test-1"),
    PlainAnchorNavbarItem("test-2"),
    NavbarHamburger(true)
  )

  val breadcrumb = BreadcrumbComponents.breadcrumb(
    BreadcrumbItem("this"),
    BreadcrumbItem("is"),
    BreadcrumbItem("a"),
    BreadcrumbItem("breadcrumb")
  )

  val card = CardComponents.card(
    header = Some(DefaultHeader("hello")),
    cardContent = contentData,
    image = Some(CardImage("https://bulma.io/images/placeholders/96x96.png")),
    footer = Some(CardFooter("hello", "dear friend"))
  )

  val dropdown = DropdownComponents.dropdown(
    trigger = Some(DropdownTrigger("dropdown")),
    menu = DropdownMenu(
      DropdownItem("hello"),
      DropdownDivider,
      DropdownItem("between dividers"),
      DropdownDivider
    )
  )

  lazy val messageHeader = MessageComponents.messageHeader("this is header")
  lazy val messageBody   = MessageComponents.messageBody("this is body")
  val message            = MessageComponents.completeStyledMessage(PrimaryColor)(messageHeader)(messageBody)

  val panel = PanelComponents.navPanel(
    Some(PanelHeading("hello")),
    PanelBlock("hi there")
  )

  val tabs = TabsComponents.customTabsContainer(Centered)(
    TabItem("hello"),
    TabItem("dear"),
    TabItem("friend")
  )

  val menu = MenuComponents.menu(
    MenuLabel("menu label"),
    MenuList("a", "b", "c")
  )

  val pagination = PaginationComponents.pagination(
    Pagination(),
    PaginationEllipsis,
    PaginationList(
      PaginationNumber(1, true),
      PaginationNumber(2),
      PaginationNumber(3)
    )
  )

  val modal = ModalComponents.activeModal(
    div(cls := "box", "hello dear")
  )

  override def pageContent: Text.all.Frag = frag(
    container(EmptyAttribute)(navBar),
    breadcrumb,
    menu,
    row(
      third1(
        dropdown
      ),
      third2(
        panel
      ),
      third3(
        tabs
      )
    ),
    row(
      half1(
        card
      ),
      half2(
        pagination
      )
    ),
    row(
      half1(
        message
      ),
      half2(
        panel
      )
    ),
    modal
  )

}
