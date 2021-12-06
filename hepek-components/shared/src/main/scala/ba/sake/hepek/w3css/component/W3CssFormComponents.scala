package ba.sake.hepek.w3css.component

import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.scalatags.all._


object W3CssFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {

    case object Default extends Type {
      override def classes = List("w3-container")
    }
  }

  object W3CssValidationStateClasses extends FormComponents.ValidationStateClasses {
    override def success: AttrPair = cls := "has-success"
    override def warning: AttrPair = cls := "has-warning"
    override def error: AttrPair   = cls := "has-error"
  }
}


final case class W3CssFormComponents(
    formType: FormComponents.Type = W3CssFormComponents.Type.Default
) extends FormComponents {
  import W3CssFormComponents._
  import BootstrapClassesBundle._

  val Companion = W3CssFormComponents

  protected override def validationStateClasses = W3CssValidationStateClasses

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
    val commonAttrs        = Seq(cls := "w3-input", inputType, inputName) ++ inputId.map(id := _) ++ inputAttrs
    val inputHelpFrag      = inputHelp.map(h => span(cls := "help-block")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help-block")(m))
    val inputValidationCls = inputValidationState.map(_.clazz)
    val inputFrag =
      if (inputType.v == "textarea") textarea(commonAttrs)("")
      else input(commonAttrs)
    val inputFragTransformed = inputTransform(inputFrag)

    formGroup(inputValidationCls.toSeq: _*)(
      inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
      inputFragTransformed,
      inputMsgsFrag,
      inputHelpFrag
    )
  }

  protected override def constructInputButton(
      inputType: AttrPair,
      inputId: Option[String],
      inputLabel: Frag,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(inputType) ++
      inputId.map(id := _) ++ inputAttrs
    val btnFrag =
      if (inputType.v == "button") button(btnClass, commonAttrs)(inputLabel)
      else input(btnClass, commonAttrs)
    btnFrag
  }

  protected override def constructInputCheckbox(
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := "checkbox", cls := "w3-check", inputName) ++
      inputId.map(id := _) ++ inputAttrs
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))
    div(
      label(inputId.map(`for` := _))(
        input(commonAttrs),
        inputLabel
      ),
      inputHelpFrag
    )
  }

  protected override def constructInputCheckboxes(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    val checkboxFrags = valueAndLabelAndAttrs.map {
      case (cbValue, cbLabel, inputAttrs) =>
        val commonAttrs =
          Seq(tpe := "checkbox", cls := "w3-check", inputName, value := cbValue) ++ inputAttrs
        label(input(commonAttrs), cbLabel)
    }

    formGroup()(
      inputLabel.map(lbl => label(lbl)),
      checkboxFrags,
      inputHelpFrag
    )
  }

  protected override def constructInputRadio(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    val radioFrags = valueAndLabelAndAttrs.map {
      case (radioValue, radioLabel, inputAttrs) =>
        val commonAttrs =
          Seq(tpe := "radio", cls := "w3-radio", inputName, value := radioValue) ++ inputAttrs

        label(input(commonAttrs), radioLabel)
    }

    formGroup()(
      inputLabel.map(lbl => label(lbl)),
      radioFrags,
      inputHelpFrag
    )
  }

  protected override def constructInputSelect(
      inputName: AttrPair,
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
    val selectAttrs = inputAttrs ++ Seq(inputName, cls := "w3-select") ++
      inputId.map(id := _)
    val selectFrag = select(selectAttrs)(optionFrags)

    formGroup()(
      inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
      selectFrag,
      inputHelpFrag
    )
  }

  protected override def constructInputSelectGrouped(
      inputName: AttrPair,
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
    val selectAttrs = inputAttrs ++ Seq(inputName, cls := "w3-select") ++
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
