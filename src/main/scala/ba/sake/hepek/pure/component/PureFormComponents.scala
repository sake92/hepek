package ba.sake.hepek.pure.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.FormComponents

object PureFormComponents extends PureFormComponents {
  // TODO check if this pattern is common enough for all frameworks/forms
  sealed trait Type { def classes: String = "pure-form" }

  object Type {
    case object Vertical extends Type {
      override def classes = super.classes + " pure-form-stacked"
    }
    case object Inline extends Type
    case object Horizontal extends Type {
      override def classes = super.classes + " pure-form-aligned"
    }
  }
}

trait PureFormComponents extends FormComponents {
  import PureFormComponents._

  def pureFormType: Type = Type.Vertical

  override def formm(_url: String, _formAttrs: AttrPair*)(
      content: Frag*
  ): Frag = {
    val newFormAttrs = _formAttrs :+ (cls := pureFormType.classes)
    form(action := _url, newFormAttrs)(content)
  }

  override def inputWithType(
      _type: String,
      _label: String,
      _inputAttrs: AttrPair*
  ) = {
    val inputId            = getAttrValue(_inputAttrs, "id")
    val inputAttrsFiltered = _inputAttrs.filterNot(_.a.name == "type") // ignore type

    pureFormType match {
      case Type.Horizontal =>
        pureHorizontalFormGroup(
          _type,
          _label,
          inputId,
          inputAttrsFiltered,
          _inputAttrs: _*
        )
      case _ =>
        if (_type == "checkbox") {
          label(cls := "pure-checkbox ", inputId.map(`for` := _))(
            input(tpe := _type, inputAttrsFiltered),
            _label
          )
        } else if (isButtonLike(_type)) {
          val inputAttrsFiltered2 =
            inputAttrsFiltered.filterNot(_.a.name == "value") // ignore value
          input(tpe := _type,
                value := _label,
                cls := "pure-button ",
                inputAttrsFiltered2)
        } else {
          frag(
            label(inputId.map(`for` := _))(_label),
            input(tpe := _type, cls := "form-control", inputAttrsFiltered)
          )
        }
    }
  }

  private def pureHorizontalFormGroup(
      _type: String,
      _label: String,
      inputId: Option[String],
      inputAttrsFiltered: Seq[AttrPair],
      _inputAttrs: AttrPair*
  ): Frag =
    if (_type == "checkbox") {
      div(cls := "pure-controls")(
        label(cls := "pure-checkbox ", inputId.map(`for` := _))(
          input(tpe := _type, inputAttrsFiltered),
          _label
        )
      )
    } else if (isButtonLike(_type)) {
      val inputAttrsFiltered2 =
        inputAttrsFiltered.filterNot(_.a.name == "value") // ignore value

      div(cls := "pure-controls")(
        input(tpe := _type,
              value := _label,
              cls := "pure-button ",
              inputAttrsFiltered2)
      )
    } else {
      div(cls := "pure-control-group")(
        label(inputId.map(`for` := _), cls := s"control-label ")(_label),
        input(tpe := _type, cls := "form-control", inputAttrsFiltered)
      )
    }
}
