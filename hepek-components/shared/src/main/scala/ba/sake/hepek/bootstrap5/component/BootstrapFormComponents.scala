package ba.sake.hepek.bootstrap5.component

import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.scalatags.all._


object BootstrapFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical extends Type

    case object Inline extends Type {
      override def classes = List("form-inline")
    }

    final case class Horizontal(labelRatio: Int = 1, inputRatio: Int = 3) extends Type {
      require(labelRatio > 0, "Label ratio < 1")
      require(inputRatio > 0, "Input ratio < 1")
      override def classes = List("form-horizontal")
    }
  }

  object BootstrapValidationStateClasses extends FormComponents.ValidationStateClasses {
    override def success: AttrPair = cls := "has-success"
    override def warning: AttrPair = cls := "has-warning"
    override def error: AttrPair   = cls := "has-error"
  }
}


final case class BootstrapFormComponents(
    formType: FormComponents.Type = BootstrapFormComponents.Type.Vertical
) extends FormComponents {
  import BootstrapFormComponents._
  import BootstrapClassesBundle._

  val Companion = BootstrapFormComponents

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
    val commonAttrs        = Seq(cls := "form-control", inputType, inputName) ++ inputId.map(id := _) ++ inputAttrs
    val inputHelpFrag      = inputHelp.map(h => span(cls := "help-block")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help-block")(m))
    val inputValidationCls = inputValidationState.map(_.clazz)
    val inputFrag =
      if (inputType.v == "textarea") textarea(commonAttrs)("") // TODO
      else input(commonAttrs)
    val inputFragTransformed = inputTransform(inputFrag)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        formGroup(inputValidationCls.toSeq: _*)(
          label(cls := s"control-label $colLabel", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := colInput)(
            inputFragTransformed,
            inputMsgsFrag,
            inputHelpFrag
          )
        )
      case _ =>
        formGroup(inputValidationCls.toSeq: _*)(
          inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
          inputFragTransformed,
          inputMsgsFrag,
          inputHelpFrag
        )
    }
  }

  protected override def constructInputButton(
      inputType: AttrPair,
      inputId: Option[String],
      inputLabel: Frag,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(inputType) ++ inputId.map(id := _) ++ inputAttrs
    val btnFrag =
      if (inputType.v == "button") button(btnClass, commonAttrs)(inputLabel)
      else input(btnClass, commonAttrs)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, false)
        formGroup()(
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
    val commonAttrs = Seq(tpe := "checkbox", inputName) ++
      inputId.map(id := _) ++ inputAttrs
    val inputHelpFrag = inputHelp.map(h => span(cls := "help-block")(h))

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, false)
        formGroup()(
          div(cls := s"$colLabel $colInput")(
            div(cls := "checkbox")(
              label(inputId.map(`for` := _))(
                input(commonAttrs),
                inputLabel
              ),
              inputHelpFrag
            )
          )
        )
      case _ =>
        div(cls := "checkbox")(
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
        label(cls := "checkbox-inline")(input(attrs), cbLabel)
      else
        div(cls := "checkbox")(
          label(input(attrs), cbLabel)
        )

    val checkboxFrags = valueAndLabelAndAttrs.map {
      case (cbValue, cbLabel, inputAttrs) =>
        val commonAttrs = Seq[AttrPair](tpe := "checkbox", inputName, value := cbValue) ++ inputAttrs
        renderCheckBox(cbLabel, commonAttrs)
    }

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        formGroup()(
          label(cls := s"control-label $colLabel")(
            inputLabel
          ),
          div(cls := colInput)(
            checkboxFrags,
            inputHelpFrag
          )
        )
      case _ =>
        formGroup()(
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

    val radioFrags = valueAndLabelAndAttrs.map {
      case (radioValue, radioLabel, inputAttrs) =>
        val commonAttrs = Seq[AttrPair](tpe := "radio", inputName, value := radioValue) ++ inputAttrs
        renderRadio(radioLabel, commonAttrs)
    }

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        formGroup()(
          label(cls := s"control-label $colLabel")(
            inputLabel
          ),
          div(cls := colInput)(
            radioFrags,
            inputHelpFrag
          )
        )
      case _ =>
        formGroup()(
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

    val optionFrags = valueAndLabelAndAttrs.map {
      case (optionValue, optionLabel, optionAttrs) =>
        val commonAttrs = Seq(value := optionValue) ++ optionAttrs
        option(commonAttrs)(optionLabel)
    }
    val selectAttrs = inputAttrs ++ Seq(inputName, cls := "form-control") ++
      inputId.map(id := _)
    val selectFrag = select(selectAttrs)(optionFrags)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        formGroup()(
          label(cls := s"control-label $colLabel", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := colInput)(
            selectFrag,
            inputHelpFrag
          )
        )
      case _ =>
        formGroup()(
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
        formGroup()(
          label(cls := s"control-label $colLabel", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := colInput)(
            selectFrag,
            inputHelpFrag
          )
        )
      case _ =>
        formGroup()(
          inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
          selectFrag,
          inputHelpFrag
        )
    }
  }

  private def formGroup(attrs: AttrPair*)(contents: Frag*): Frag =
    div(cls := "form-group", attrs)(contents)

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
