package ba.sake.hepek.bulma.component

import scalatags.Text.all._
import ba.sake.stone.Wither
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle

object BulmaFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical                                            extends Type
    case class Horizontal(labelRatio: Int = 1, inputRatio: Int = 3) extends Type
  }
  val DefaultType = Type.Vertical

  object BulmaValidationStateClasses extends FormComponents.ValidationStateClasses {
    override def success: String = "is-success"
    override def warning: String = "is-warning"
    override def error: String   = "is-error"
  }
}

@Wither
case class BulmaFormComponents(
    formType: FormComponents.Type = BulmaFormComponents.DefaultType
) extends ba.sake.hepek.plain.component.PlainFormComponentsImpl {
  import BulmaFormComponents._
  import BulmaClassesBundle._

  val Companion = BulmaFormComponents

  override def validationStateClasses = BulmaValidationStateClasses

  // https://github.com/jgthms/bulma/issues/886#issuecomment-335584165
  override def formFieldset(legendTitle: String)(content: Frag*) =
    fieldset(cls := "box")(
      legend(cls := "label has-text-centered")(legendTitle),
      content
    )

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
    val inputCls = if (inputType == "textarea") "textarea" else "input"
    val commonAttrs = Seq(cls := inputCls, tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputHelpFrag      = inputHelp.map(h => span(cls := "help")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help")(m))
    val inputValidationCls = inputValidationState.map(cls := _.clazz)
    val inputFrag =
      if (inputType == "textarea") textarea(commonAttrs, inputValidationCls)(inputValue)
      else input(commonAttrs, inputValidationCls)
    val inputFragTransformed = inputTransform(inputFrag)

    val bulmaField =
      div(cls := "field")(
        inputLabel
          .filterNot(_ => formType.isInstanceOf[Type.Horizontal]) // ignore if horizontal
          .map(l => label(cls := "label", inputId.map(`for` := _))(inputLabel)),
        div(cls := "control")(
          inputFragTransformed
        ),
        inputMsgsFrag,
        inputHelpFrag
      )

    formType match {
      case _: Type.Horizontal =>
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
      inputLabel: Frag,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val btnField =
      if (inputType == "button") button(btnClass, commonAttrs)(inputLabel)
      else input(btnClass, commonAttrs)

    val bulmaField =
      div(cls := "field")(
        div(cls := "control")(
          btnField
        )
      )

    formType match {
      case _: Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")( /* empty */ ),
          div(cls := "field-body")(bulmaField)
        )
      case Type.Vertical =>
        bulmaField
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

    // TODO check nesting needed... !!?
    val bulmaField =
      div(cls := "field")(
        div(cls := "control")(
          label(cls := "checkbox")(
            input(commonAttrs),
            inputLabel
          )
        )
      )

    formType match {
      case _: Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")( /* empty */ ),
          div(cls := "field-body")(bulmaField)
        )
      case Type.Vertical =>
        bulmaField
    }
  }

  override def constructInputCheckboxes(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help")(h))

    def renderCheckBox(cbLabel: String, attrs: Seq[AttrPair]) =
      label(cls := "checkbox")(
        input(attrs),
        cbLabel
      )

    val checkboxFrags = valueAndLabelAndAttrs.map {
      case (cbValue, cbLabel, inputAttrs) =>
        val commonAttrs = Seq(tpe := "checkbox", name := inputName, value := cbValue) ++ inputAttrs
        renderCheckBox(cbLabel, commonAttrs)
    }

    val bulmaField = div(cls := "field")(
      div(cls := "control")(checkboxFrags)
    )

    formType match {
      case _: Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")( /* empty */ ),
          div(cls := "field-body")(bulmaField)
        )
      case Type.Vertical =>
        bulmaField
    }
  }

  override def constructInputRadio(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help")(h))

    def renderRadio(radioLabel: String, attrs: Seq[AttrPair]) =
      label(cls := "radio")(
        input(attrs),
        radioLabel
      )

    val radioFrags = valueAndLabelAndAttrs.map {
      case (radioValue, radioLabel, inputAttrs) =>
        val commonAttrs = Seq(tpe := "radio", name := inputName, value := radioValue) ++ inputAttrs
        renderRadio(radioLabel, commonAttrs)
    }

    val bulmaField = div(cls := "field")(
      div(cls := "control")(radioFrags)
    )

    formType match {
      case _: Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")( /* empty */ ),
          div(cls := "field-body")(bulmaField)
        )
      case Type.Vertical =>
        bulmaField
    }
  }

  // TODO constructInputSelectGrouped..
}
