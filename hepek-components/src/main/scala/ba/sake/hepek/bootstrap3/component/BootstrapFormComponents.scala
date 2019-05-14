package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.FormComponents

object BootstrapFormComponents extends BootstrapFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical extends Type
    case object Inline extends Type {
      override def classes = "form-inline"
    }
    case class Horizontal(labelRatio: Int = 1, inputRatio: Int = 3) extends Type {
      require(labelRatio > 0, "Label ratio < 1")
      require(inputRatio > 0, "Input ratio < 1")
      override def classes = "form-horizontal"
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

  override def validationStateClasses = BootstrapValidationStateClasses

  override def formType: FormComponents.Type = Type.Vertical

  override def inputWithType(
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

    val inputHelpFrag      = inputHelp.map(h => span(cls := "help-block")(h))
    val inputMsgsFrag      = inputMessages.map(m => span(cls := "help-block")(m))
    val inputValidationCls = inputValidationState.map(cls := _.classes)

    // helper for horizontal form
    def bsHorizontalFormGroup(labelRatioBootstrap: Int, inputRatioBootstrap: Int): Frag =
      if (inputType == "checkbox")
        div(cls := "form-group")(
          div(cls := s"col-sm-offset-$labelRatioBootstrap col-sm-$inputRatioBootstrap")(
            div(cls := "checkbox")(
              label(inputId.map(`for` := _))(
                input(commonAttrs),
                inputLabel
              )
            )
          )
        )
      else if (isButtonLike(inputType))
        div(cls := "form-group")(
          div(cls := s"col-sm-offset-$labelRatioBootstrap col-sm-$inputRatioBootstrap")(
            input(cls := "btn ", commonAttrs)
          )
        )
      else
        div(cls := "form-group ", inputValidationCls)(
          label(cls := s"control-label col-sm-$labelRatioBootstrap", inputId.map(`for` := _))(
            inputLabel
          ),
          div(cls := s"col-sm-$inputRatioBootstrap")(
            input(cls := "form-control ", commonAttrs),
            inputMsgsFrag,
            inputHelpFrag
          )
        )

    formType match {
      case Type.Horizontal(labelRatio, inputRatio) =>
        val labelRatioBootstrap =
          ((labelRatio / (labelRatio + inputRatio).toDouble) * 12).toInt
        val inputRatioBootstrap =
          ((inputRatio / (labelRatio + inputRatio).toDouble) * 12).toInt
        bsHorizontalFormGroup(labelRatioBootstrap, inputRatioBootstrap)
      case _ =>
        if (inputType == "checkbox")
          div(cls := "checkbox")(
            label(inputId.map(`for` := _))(
              input(commonAttrs),
              inputLabel
            )
          )
        else if (isButtonLike(inputType))
          input(
            cls := "btn ",
            commonAttrs
          )
        else
          div(cls := "form-group ", inputValidationCls)(
            inputLabel match {
              case None =>
                input(cls := "form-control ", commonAttrs)
              case Some(inputLabel) =>
                frag(
                  label(inputId.map(`for` := _))(inputLabel),
                  input(cls := "form-control ", commonAttrs)
                )
            },
            inputMsgsFrag,
            inputHelpFrag
          )
    }
  }

}
