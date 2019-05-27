package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle

object BootstrapFormComponents extends BootstrapFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical extends Type
    case object Inline extends Type {
      override def classes = List("form-inline")
    }
    case class Horizontal(labelRatio: Int = 1, inputRatio: Int = 3) extends Type {
      require(labelRatio > 0, "Label ratio < 1")
      require(inputRatio > 0, "Input ratio < 1")
      override def classes = List("form-horizontal")
    }
  }

  object BootstrapValidationStateClasses extends FormComponents.ValidationStateClasses {
    override def success: String = "has-success"
    override def warning: String = "has-warning"
    override def error: String   = "has-error"
  }
}

trait BootstrapFormComponents extends FormComponents {
  import BootstrapFormComponents._
  import BootstrapClassesBundle._

  override def validationStateClasses = BootstrapValidationStateClasses

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
    val commonAttrs = Seq(cls := "form-control", tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputHelpFrag      = inputHelp.map(h => span(cls := "help-block")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help-block")(m))
    val inputValidationCls = inputValidationState.map(cls := _.clazz)
    val inputFieldContent =
      if (inputType == "textarea") textarea(commonAttrs)(inputValue)
      else input(commonAttrs)

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, true)
        formGroup(inputValidationCls.toSeq: _*)(
          label(cls := s"control-label $colLabel", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := colInput)(
            inputFieldContent,
            inputMsgsFrag,
            inputHelpFrag
          )
        )
      case _ =>
        formGroup(inputValidationCls.toSeq: _*)(
          inputLabel.map(lbl => label(inputId.map(`for` := _))(lbl)),
          inputFieldContent,
          inputMsgsFrag,
          inputHelpFrag
        )
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

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, false)
        formGroup()(
          div(cls := s"$colLabel $colInput")(
            btnField
          )
        )
      case _ =>
        btnField
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

    formType match {
      case ft: Type.Horizontal =>
        val (colLabel, colInput) = horizontalRatioClasses(ft, false)
        formGroup()(
          div(cls := s"$colLabel $colInput")(
            div(cls := "checkbox")(
              label(inputId.map(`for` := _))(
                input(commonAttrs),
                inputLabel
              )
            )
          )
        )
      case _ =>
        div(cls := "checkbox")(
          label(inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          )
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
