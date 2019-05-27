package ba.sake.hepek.bulma.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle

object BulmaFormComponents extends BulmaFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical   extends Type
    case object Horizontal extends Type
  }

  object BulmaValidationStateClasses extends FormComponents.ValidationStateClasses {
    override def success: String = "is-success"
    override def warning: String = "is-warning"
    override def error: String   = "is-error"
  }
}

trait BulmaFormComponents extends FormComponents {
  import BulmaFormComponents._
  import BulmaClassesBundle._

  override def validationStateClasses = BulmaValidationStateClasses

  override def formType: FormComponents.Type = Type.Vertical

  override def constructInputNormal(
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
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputHelpFrag      = inputHelp.map(h => span(cls := "help")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help")(m))
    val inputValidationCls = inputValidationState.map(cls := _.clazz)

    val bulmaField =
      div(cls := "field")(
        inputLabel
          .filterNot(_ => formType == Type.Horizontal) // ignore if horizontal
          .map(l => label(cls := "label", inputId.map(`for` := _))(inputLabel)),
        div(cls := "control")(
          input(cls := "input", commonAttrs, inputValidationCls)
        ),
        inputMsgsFrag,
        inputHelpFrag
      )

    formType match {
      case Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")(
            // ignore label if checkbox
            label(cls := "label", inputId.map(`for` := _))(
              inputLabel.filterNot(_ => inputType == "checkbox")
            )
          ),
          div(cls := "field-body")(
            bulmaField
          )
        )
      case Type.Vertical =>
        bulmaField
    }
  }

  override def constructInputButton(
      inputType: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val btnField =
      if (inputType == "button") button(btnClass, commonAttrs)(inputValue)
      else input(btnClass, commonAttrs)

    val bulmaField =
      div(cls := "field")(
        div(cls := "control")(
          btnField
        )
      )

    formType match {
      case Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")(
            // no label
          ),
          div(cls := "field-body")(
            bulmaField
          )
        )
      case Type.Vertical =>
        bulmaField
    }
  }

  override def constructInputCheckbox(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    val bulmaField =
      div(cls := "field")(
        div(cls := "control")(
          label(cls := "checkbox", inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          )
        )
      )

    formType match {
      case Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")(
            // ignore label if checkbox
            label(cls := "label", inputId.map(`for` := _))(
              inputLabel.filterNot(_ => inputType == "checkbox")
            )
          ),
          div(cls := "field-body")(
            bulmaField
          )
        )
      case Type.Vertical =>
        bulmaField
    }
  }

}
