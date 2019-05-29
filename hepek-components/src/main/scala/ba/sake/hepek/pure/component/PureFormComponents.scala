package ba.sake.hepek.pure.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.pure.component.classes.PureButtonClasses

object PureFormComponents extends PureFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical extends Type {
      override def classes = List("pure-form", "pure-form-stacked")
    }
    case object Inline extends Type {
      override def classes = List("pure-form")
    }
    case object Horizontal extends Type {
      override def classes = List("pure-form", "pure-form-aligned")
    }
  }
}

trait PureFormComponents extends FormComponents {
  import PureFormComponents._
  import PureButtonClasses._

  // TODO display validation !!
  // TODO implement checkbox, radio etc

  override def formType: FormComponents.Type = Type.Vertical

  override def constructInputNormal(
      inputType: String,
      inputName: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String],
      inputAttrs: Seq[AttrPair],
      inputTransform: Frag => Frag
  ) = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputFieldContent =
      if (inputType == "textarea") textarea(commonAttrs)(inputValue)
      else input(commonAttrs)

    formType match {
      case Type.Horizontal =>
        div(cls := "pure-control-group")(
          label(inputId.map(`for` := _))(inputLabel),
          inputFieldContent
        )
      case _ =>
        frag(
          label(inputId.map(`for` := _))(inputLabel),
          inputFieldContent
        )
    }
  }

  override def constructInputButton(
      inputType: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Frag,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val btnField =
      if (inputType == "button") button(btnClass, commonAttrs)(inputLabel)
      else input(btnClass, commonAttrs)

    formType match {
      case Type.Horizontal =>
        div(cls := "pure-controls")(
          btnField
        )
      case _ =>
        btnField
    }
  }

  override def constructInputCheckbox(
      inputName: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := "checkbox", name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    formType match {
      case Type.Horizontal =>
        div(cls := "pure-controls")(
          label(cls := "pure-checkbox", inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          )
        )
      case _ =>
        label(cls := "pure-checkbox", inputId.map(`for` := _))(
          input(commonAttrs),
          inputLabel
        )
    }
  }

}
