package ba.sake.hepek.pure.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.FormComponents

object PureFormComponents extends PureFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical extends Type {
      override def classes = "pure-form pure-form-stacked"
    }
    case object Inline extends Type {
      override def classes = "pure-form"
    }
    case object Horizontal extends Type {
      override def classes = "pure-form pure-form-aligned"
    }
  }
}

trait PureFormComponents extends FormComponents {
  import PureFormComponents._

  override def formType: FormComponents.Type = Type.Vertical

  override def inputWithType(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputHelp: Option[String],
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String],
      inputAttrs: Seq[AttrPair]
  ) = {
    // TODO display validation !!
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    formType match {
      case Type.Horizontal =>
        pureHorizontalFormGroup(
          inputType,
          inputName,
          inputLabel,
          inputId,
          inputValue,
          inputAttrs
        )
      case _ =>
        if (inputType == "checkbox")
          label(cls := "pure-checkbox", inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          )
        else if (isButtonLike(inputType))
          input(
            cls := "pure-button",
            commonAttrs
          )
        else
          frag(
            label(inputId.map(`for` := _))(inputLabel),
            input(commonAttrs)
          )

    }
  }

  private def pureHorizontalFormGroup(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputAttrs: Seq[AttrPair]
  ) = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    if (inputType == "checkbox")
      div(cls := "pure-controls")(
        label(cls := "pure-checkbox", inputId.map(`for` := _))(
          input(commonAttrs),
          inputLabel
        )
      )
    else if (isButtonLike(inputType))
      div(cls := "pure-controls")(
        input(cls := "pure-button", commonAttrs)
      )
    else
      div(cls := "pure-control-group")(
        label(inputId.map(`for` := _), cls := s"control-label")(inputLabel),
        input(commonAttrs)
      )
  }
}
