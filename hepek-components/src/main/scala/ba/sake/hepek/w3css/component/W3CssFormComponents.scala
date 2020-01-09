package ba.sake.hepek.w3css.component

import scalatags.Text.all._
import ba.sake.stone.Wither
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle

object W3CssFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {

    case object Default extends Type {
      override def classes = List("w3-container")
    }
  }

  object W3CssValidationStateClasses extends FormComponents.ValidationStateClasses {
    override def success: String = "has-success"
    override def warning: String = "has-warning"
    override def error: String   = "has-error"
  }
}

@Wither
case class W3CssFormComponents(
    formType: FormComponents.Type = W3CssFormComponents.Type.Default
) extends FormComponents {
  import W3CssFormComponents._
  import BootstrapClassesBundle._

  val Companion = W3CssFormComponents

  override def validationStateClasses = W3CssValidationStateClasses

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
    val commonAttrs = Seq(cls := "w3-input", tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputHelpFrag      = inputHelp.map(h => span(cls := "help-block")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help-block")(m))
    val inputValidationCls = inputValidationState.map(cls := _.clazz)
    val inputFrag =
      if (inputType == "textarea") textarea(commonAttrs)(inputValue)
      else input(commonAttrs)
    val inputFragTransformed = inputTransform(inputFrag)

    formGroup(inputValidationCls.toSeq: _*)(
      inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
      inputFragTransformed,
      inputMsgsFrag,
      inputHelpFrag
    )
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
    val btnFrag =
      if (inputType == "button") button(btnClass, commonAttrs)(inputLabel)
      else input(btnClass, commonAttrs)
    btnFrag
  }

  override def constructInputCheckbox(
      inputName: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := "checkbox", cls := "w3-check", name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))
    div(
      label(inputId.map(`for` := _))(
        input(commonAttrs),
        inputLabel
      ),
      inputHelpFrag
    )
  }

  override def constructInputCheckboxes(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    val checkboxFrags = valueAndLabelAndAttrs.map {
      case (cbValue, cbLabel, inputAttrs) =>
        val commonAttrs =
          Seq(tpe := "checkbox", cls := "w3-check", name := inputName, value := cbValue) ++ inputAttrs
        label(input(commonAttrs), cbLabel)
    }

    formGroup()(
      inputLabel.map(lbl => label(lbl)),
      checkboxFrags,
      inputHelpFrag
    )
  }

  override def constructInputRadio(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    val radioFrags = valueAndLabelAndAttrs.map {
      case (radioValue, radioLabel, inputAttrs) =>
        val commonAttrs =
          Seq(tpe := "radio", cls := "w3-radio", name := inputName, value := radioValue) ++ inputAttrs

        label(input(commonAttrs), radioLabel)
    }

    formGroup()(
      inputLabel.map(lbl => label(lbl)),
      radioFrags,
      inputHelpFrag
    )
  }

  override def constructInputSelect(
      inputName: String,
      inputId: Option[String],
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    val optionFrags = valueAndLabelAndAttrs.map {
      case (optionValue, optionLabel, optionAttrs) =>
        val commonAttrs = Seq(value := optionValue) ++ optionAttrs
        option(commonAttrs)(optionLabel)
    }
    val selectAttrs = inputAttrs ++ Seq(name := inputName, cls := "w3-select") ++
      inputId.map(id := _)
    val selectFrag = select(selectAttrs)(optionFrags)

    formGroup()(
      inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
      selectFrag,
      inputHelpFrag
    )
  }

  override def constructInputSelectGrouped(
      inputName: String,
      inputId: Option[String],
      valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    val optionGroupFrags = valueAndLabelAndAttrsGrouped.map {
      case (optGroupLabel, valueAndLabelAndAttrs) =>
        val optionFrags = valueAndLabelAndAttrs.map {
          case (optionValue, optionLabel, optionAttrs) =>
            val commonAttrs = Seq(value := optionValue) ++ optionAttrs
            option(commonAttrs)(optionLabel)
        }
        optgroup(attr("label") := optGroupLabel)(optionFrags)
    }
    val selectAttrs = inputAttrs ++ Seq(name := inputName, cls := "w3-select") ++
      inputId.map(id := _)
    val selectFrag = select(selectAttrs)(optionGroupFrags)

    formGroup()(
      inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
      selectFrag,
      inputHelpFrag
    )
  }

  private def formGroup(attrs: AttrPair*)(contents: Frag*): Frag =
    div(attrs)(contents)
}
