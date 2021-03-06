package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.scalatags.all._
import ba.sake.kalem.Wither

object BulmaFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical                                            extends Type
    case class Horizontal(labelRatio: Int = 1, inputRatio: Int = 3) extends Type
  }
  val DefaultType = Type.Vertical

  object BulmaValidationStateClasses extends FormComponents.ValidationStateClasses {
    override def success: AttrPair = cls := "is-success"
    override def warning: AttrPair = cls := "is-warning"
    override def error: AttrPair   = cls := "is-error"
  }
}

@Wither
final case class BulmaFormComponents(
    formType: FormComponents.Type = BulmaFormComponents.DefaultType
) extends FormComponents {
  import BulmaFormComponents._
  import BulmaClassesBundle._

  val Companion = BulmaFormComponents

  protected override def validationStateClasses = BulmaValidationStateClasses

  // https://github.com/jgthms/bulma/issues/886#issuecomment-335584165
  override def formFieldset(legendTitle: String)(content: Frag*) =
    fieldset(cls := "box")(
      legend(cls := "label has-text-centered")(legendTitle),
      content
    )

  protected override def constructInputNormal(
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
    val inputCls           = if (inputType.v == "textarea") "textarea" else "input"
    val commonAttrs        = Seq(cls := inputCls, inputType, inputName) ++ inputId.map(id := _) ++ inputAttrs
    val inputHelpFrag      = inputHelp.map(h => span(cls := "help")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help")(m))
    val inputValidationCls = inputValidationState.map(_.clazz)
    val inputFrag =
      if (inputType.v == "textarea") textarea(commonAttrs, inputValidationCls)("")
      else input(commonAttrs, inputValidationCls)
    val inputFragTransformed = inputTransform(inputFrag)

    val bulmaField =
      div(cls := "field")(
        inputLabel
          .filterNot(_ => formType.isInstanceOf[Type.Horizontal]) // ignore if horizontal
          .map(l => label(cls := "label", inputId.map(`for` := _.v))(inputLabel)),
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
            label(cls := "label", inputId.map(`for` := _.v))(
              inputLabel.filterNot(_ => inputType.v == "checkbox")
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

  protected override def constructInputButton(
      inputType: AttrPair,
      inputId: Option[String],
      inputLabel: Frag,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(inputType) ++
      inputId.map(id := _) ++ inputAttrs
    val btnField =
      if (inputType.v == "button") button(btnClass, commonAttrs)(inputLabel)
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

  protected override def constructInputCheckbox(
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := "checkbox", inputName) ++
      inputId.map(id := _) ++ inputAttrs

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

  protected override def constructInputCheckboxes(
      inputName: AttrPair,
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
        val commonAttrs = Seq[AttrPair](tpe := "checkbox", inputName, value := cbValue) ++ inputAttrs
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

  protected override def constructInputRadio(
      inputName: AttrPair,
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
        val commonAttrs = Seq[AttrPair](tpe := "radio", inputName, value := radioValue) ++ inputAttrs
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

  protected override def constructInputSelect(
      inputName: AttrPair,
      inputId: Option[String],
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val optionFrags = valueAndLabelAndAttrs.map {
      case (optionValue, optionLabel, optionAttrs) =>
        val commonAttrs = Seq(value := optionValue) ++ optionAttrs
        option(commonAttrs)(optionLabel)
    }
    val selectAttrs = inputAttrs ++ Seq(inputName) ++
      inputId.map(id := _)
    val selectFrag = select(selectAttrs)(optionFrags)
    val bulmaFrag  = div(cls := "select is-fullwidth")(selectFrag)

    formType match {
      case _: Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")(
            label(cls := "label", inputId.map(`for` := _.v))(
              inputLabel
            )
          ),
          div(cls := "field-body")(bulmaFrag)
        )
      case Type.Vertical =>
        bulmaFrag
    }
  }

  protected override def constructInputSelectGrouped(
      inputName: AttrPair,
      inputId: Option[String],
      valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val optionGroupFrags = valueAndLabelAndAttrsGrouped.map {
      case (optGroupLabel, valueAndLabelAndAttrs) =>
        val optionFrags = valueAndLabelAndAttrs.map {
          case (optionValue, optionLabel, optionAttrs) =>
            val commonAttrs = Seq(value := optionValue) ++ optionAttrs
            option(commonAttrs)(optionLabel)
        }
        optgroup(attr("label") := optGroupLabel)(optionFrags)
    }
    val selectAttrs = inputAttrs ++ Seq(inputName) ++ inputId.map(id := _)
    val selectFrag  = select(selectAttrs)(optionGroupFrags)
    val bulmaFrag   = div(cls := "select is-fullwidth")(selectFrag)

    formType match {
      case _: Type.Horizontal =>
        div(cls := "field is-horizontal")(
          div(cls := "field-label")(
            label(cls := "label", inputId.map(`for` := _.v))(
              inputLabel
            )
          ),
          div(cls := "field-body")(bulmaFrag)
        )
      case Type.Vertical =>
        bulmaFrag
    }
  }
}
