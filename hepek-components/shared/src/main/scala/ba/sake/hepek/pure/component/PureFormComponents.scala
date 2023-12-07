package ba.sake.hepek.pure.component

import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.plain.component.PlainFormComponentsImpl
import ba.sake.hepek.scalatags.all._

object PureFormComponents:

  enum Type(override val classes: List[String]) extends FormComponents.Type:
    case Vertical   extends Type(List("pure-form", "pure-form-stacked"))
    case Inline     extends Type(List("pure-form"))
    case Horizontal extends Type(List("pure-form", "pure-form-aligned"))

trait PureFormComponents extends PlainFormComponentsImpl {
  import PureFormComponents._
  import classes.PureClassesBundle._

  val Companion = PureFormComponents

  // TODO display validation !!
  // TODO implement checkbox, radio etc

  override def formType: FormComponents.Type = Type.Vertical

  override def constructInputNormal(
      inputType: AttrPair,
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String],
      inputAttrs: Seq[AttrPair],
      inputTransform: Frag => Frag
  ) = {
    val commonAttrs = Seq(inputType, inputName) ++
      inputId.map(id := _) ++ inputAttrs
    val inputFieldContent =
      if (inputType.v == "textarea") textarea(commonAttrs)("")
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
      inputType: AttrPair,
      inputId: Option[String],
      inputLabel: String,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(inputType) ++
      inputId.map(id := _) ++ inputAttrs

    val btnField = input(btnClass, commonAttrs)

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
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := "checkbox", inputName) ++
      inputId.map(id := _) ++ inputAttrs

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
