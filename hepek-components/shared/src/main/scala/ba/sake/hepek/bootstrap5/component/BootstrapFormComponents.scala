package ba.sake.hepek.bootstrap5.component

import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.scalatags.all.*
import BootstrapFormComponents.*
import BootstrapClassesBundle.*

// https://getbootstrap.com/docs/5.2/forms/validation/#server-side
// TODO revisit https://getbootstrap.com/docs/5.0/forms/layout/#horizontal-form
final class BootstrapFormComponents private (
    val formType: BootstrapFormComponents.Type
) extends FormComponents {

  val Companion = BootstrapFormComponents

  def withType(formType: BootstrapFormComponents.Type): BootstrapFormComponents =
    new BootstrapFormComponents(formType)

  protected override def validationStateClasses = BootstrapValidationStateClasses

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
    val commonAttrs =
      Seq(cls          := "form-control", inputType, inputName) ++
        inputId.map(id := _) ++
        inputValidationState.map(_.clazz) ++ inputAttrs
    val inputFrag =
      if (inputType.v == "textarea")
        textarea(commonAttrs.filterNot(_.a.name == "value"))(getAttrValue(inputAttrs, "value"))
      else input(commonAttrs)
    val inputFragTransformed = inputTransform(inputFrag)
    val inputMsgsFrag = inputMessages.map(m => span(inputValidationState.map(_.feedbackClazz))(m))
    val inputHelpFrag = inputHelp.map(h => span(inputValidationState.map(_.feedbackClazz))(h))

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        div(cls := "mb-3")(
          label(cls := s"col-form-label $colLabel", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := colInput)(
            inputGroup()(
              inputFragTransformed,
              inputMsgsFrag,
              inputHelpFrag
            )
          )
        )
      case _ =>
        div(cls := "mb-3")(
          inputLabel.map(lbl => label(cls := "form-label", inputId.map(`for` := _))(lbl)),
          inputGroup()(
            inputFragTransformed,
            inputMsgsFrag,
            inputHelpFrag
          )
        )
    }
  }

  protected override def constructInputButton(
      inputType: AttrPair,
      inputId: Option[String],
      inputLabel: String,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val attrs   = Seq(inputType, btnClass) ++ inputId.map(id := _) ++ inputAttrs
    val btnFrag = input(attrs)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, false)
        inputGroup()(
          div(cls := s"$colLabel $colInput")(
            btnFrag
          )
        )
      case _ =>
        btnFrag
    }
  }

  protected override def constructButton(
      inputLabel: Frag,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val attrs   = inputAttrs.appended(btnClass)
    val btnFrag = button(attrs)(inputLabel)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, false)
        inputGroup()(
          div(cls := s"$colLabel $colInput")(
            btnFrag
          )
        )
      case _ =>
        btnFrag
    }
  }

  protected override def constructInputCheckbox(
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := "checkbox", cls := "form-check-input", inputName) ++
      inputId.map(id := _) ++ inputAttrs
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, false)
        inputGroup()(
          div(cls := s"$colLabel $colInput")(
            div(cls := "form-check")(
              label(inputId.map(`for` := _))(
                input(commonAttrs),
                inputLabel
              ),
              inputHelpFrag
            )
          )
        )
      case _ =>
        div(cls := "form-check")(
          label(inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          ),
          inputHelpFrag
        )
    }
  }

  protected override def constructInputCheckboxes(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    def renderCheckBox(cbLabel: String, attrs: Seq[AttrPair]) =
      if (isInline)
        label(cls := "form-check-inline")(input(attrs), cbLabel)
      else
        div(cls := "form-check")(
          label(input(attrs), cbLabel)
        )

    val checkboxFrags = valueAndLabelAndAttrs.map { case (cbValue, cbLabel, inputAttrs) =>
      val commonAttrs = Seq[AttrPair](
        tpe := "checkbox",
        cls := "form-check-input",
        inputName,
        value := cbValue
      ) ++ inputAttrs
      renderCheckBox(cbLabel, commonAttrs)
    }

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        inputGroup()(
          label(cls := s"col-form-label $colLabel")(
            inputLabel
          ),
          div(cls := colInput)(
            checkboxFrags,
            inputHelpFrag
          )
        )
      case _ =>
        inputGroup()(
          inputLabel.map(lbl => label(lbl)),
          checkboxFrags,
          inputHelpFrag
        )
    }
  }

  protected override def constructInputRadio(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag = {
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    def renderRadio(radioLabel: String, attrs: Seq[AttrPair]) =
      if (isInline)
        label(cls := "radio-inline")(input(attrs), radioLabel)
      else
        div(cls := "radio")(
          label(input(attrs), radioLabel)
        )

    val radioFrags = valueAndLabelAndAttrs.map { case (radioValue, radioLabel, inputAttrs) =>
      val commonAttrs = Seq[AttrPair](tpe := "radio", inputName, value := radioValue) ++ inputAttrs
      renderRadio(radioLabel, commonAttrs)
    }

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        inputGroup()(
          label(cls := s"col-form-label $colLabel")(
            inputLabel
          ),
          div(cls := colInput)(
            radioFrags,
            inputHelpFrag
          )
        )
      case _ =>
        inputGroup()(
          inputLabel.map(lbl => label(lbl)),
          radioFrags,
          inputHelpFrag
        )
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
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    val optionFrags = valueAndLabelAndAttrs.map { case (optionValue, optionLabel, optionAttrs) =>
      val commonAttrs = Seq(value := optionValue) ++ optionAttrs
      option(commonAttrs)(optionLabel)
    }
    val selectAttrs = inputAttrs ++ Seq(inputName, cls := "form-control") ++
      inputId.map(id := _)
    val selectFrag = select(selectAttrs)(optionFrags)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        inputGroup()(
          label(cls := s"col-form-label $colLabel", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := colInput)(
            selectFrag,
            inputHelpFrag
          )
        )
      case _ =>
        inputGroup()(
          inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
          selectFrag,
          inputHelpFrag
        )
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
    val selectAttrs = inputAttrs ++ Seq(inputName, cls := "form-control") ++
      inputId.map(id := _)
    val selectFrag = select(selectAttrs)(optionGroupFrags)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        inputGroup()(
          label(cls := s"col-form-label $colLabel", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := colInput)(
            selectFrag,
            inputHelpFrag
          )
        )
      case _ =>
        inputGroup()(
          inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
          selectFrag,
          inputHelpFrag
        )
    }
  }

  private def inputGroup(attrs: AttrPair*)(contents: Frag*): Frag =
    div(cls := "input-group mb-3 has-validation", attrs)(contents)

  private def horizontalRatioClasses(
      ht: Type.Horizontal,
      hasLabel: Boolean
  ): (String, String) = {
    val labelRatio = ((ht.labelRatio / (ht.labelRatio + ht.inputRatio).toDouble) * 12).toInt
    val inputRatio = ((ht.inputRatio / (ht.labelRatio + ht.inputRatio).toDouble) * 12).toInt
    val lblCls     = if (hasLabel) "col-sm" else "col-sm-offset"
    s"$lblCls-$labelRatio" -> s"col-sm-$inputRatio"
  }
}

object BootstrapFormComponents:

  val default: BootstrapFormComponents =
    new BootstrapFormComponents(BootstrapFormComponents.Type.Vertical)

  enum Type(override val classes: List[String]) extends FormComponents.Type:
    case Vertical                                             extends Type(List.empty)
    case Inline                                               extends Type(List("form-inline"))
    case Horizontal(val labelRatio: Int, val inputRatio: Int) extends Type(List("form-horizontal"))

  object BootstrapValidationStateClasses extends FormComponents.ValidationStateClasses:
    override def success: AttrPair         = cls := "is-valid"
    override def warning: AttrPair         = cls := "is-invalid"
    override def error: AttrPair           = cls := "is-invalid"
    override def successFeedback: AttrPair = cls := "valid-feedback"
    override def warningFeedback: AttrPair = cls := "invalid-feedback"
    override def errorFeedback: AttrPair   = cls := "invalid-feedback"
