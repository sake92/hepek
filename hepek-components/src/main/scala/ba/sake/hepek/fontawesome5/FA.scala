package ba.sake.hepek.fontawesome5

import scalatags.Text.all._
import ba.sake.stone.Wither

object FA {
  val clock = FA5Icon("clock")

  // brands
  val github = FA5Icon("github", FA5Icon.Type.Brand)
  val gitter = FA5Icon("gitter", FA5Icon.Type.Brand)
}

@Wither
case class FA5Icon(
    name: String,
    tpe: FA5Icon.Type = FA5Icon.Type.Solid,
    size: Option[FA5Icon.Size] = None,
    animation: Option[FA5Icon.Animation] = None,
    flip: Option[FA5Icon.Flip] = None,
    rotate: Option[FA5Icon.Rotate] = None
) {
  def regular = withTpe(FA5Icon.Type.Regular)
  def solid   = withTpe(FA5Icon.Type.Solid)

  def spinning = withAnimation(FA5Icon.Animation.Spin)
  def pulsing  = withAnimation(FA5Icon.Animation.Pulse)

  def apply(): Frag = {
    val allClasses: List[String] = List(s"fa-$name") ++ tpe.classes ++
      size.toList.flatMap(_.classes) ++
      animation.toList.flatMap(_.classes) ++
      flip.toList.flatMap(_.classes) ++
      rotate.toList.flatMap(_.classes)
    i(allClasses.map(cls := _))()
  }
}

object FA5Icon {
  sealed trait Type { def classes: List[String] }

  object Type {
    // free
    case object Solid extends Type { def classes = List("fas") }
    case object Brand extends Type { def classes = List("fab") }
    // premium
    case object Regular extends Type { override def classes = List("far") }
  }

  sealed trait Size { def classes: List[String] }

  object Size {
    case object Xs      extends Size { def classes = List("fa-xs")  }
    case object Sm      extends Size { def classes = List("fa-sm")  }
    case object lg      extends Size { def classes = List("fa-lg")  }
    case object Times2  extends Size { def classes = List("fa-2x")  }
    case object Times5  extends Size { def classes = List("fa-5x")  }
    case object Times10 extends Size { def classes = List("fa-10x") }
  }

  sealed trait Animation { def classes: List[String] }

  object Animation {
    case object Spin  extends Animation { def classes = List("fa-spin")  }
    case object Pulse extends Animation { def classes = List("fa-pulse") }
  }

  sealed trait Flip { def classes: List[String] }

  object Flip {
    case object Horizontal extends Flip { def classes = List("fa-flip-horizontal") }
    case object Vertical   extends Flip { def classes = List("fa-flip-vertical")   }
  }

  sealed trait Rotate { def classes: List[String] }

  object Rotate {
    case object Rot90  extends Rotate { def classes = List("fa-rotate-90")  }
    case object Rot180 extends Rotate { def classes = List("fa-rotate-180") }
    case object Rot270 extends Rotate { def classes = List("fa-rotate-270") }
  }
}
