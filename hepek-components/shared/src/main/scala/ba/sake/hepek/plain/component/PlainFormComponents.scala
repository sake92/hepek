package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.scalatags.all

import all.{form => _, _}

object PlainFormComponents {
  val DefaultType = new FormComponents.Type {}

  trait ValidationStateClasses {
    def success: String = "success"
    def warning: String = "warning"
    def error: String   = "error"
  }
}

final case class PlainFormComponents(
    formType: FormComponents.Type = PlainFormComponents.DefaultType
) extends PlainFormComponentsImpl

// handy to extend for INCOMPLETE frameworks (see Pure, Bulma..)
trait PlainFormComponentsImpl extends FormComponents {
  import FormComponents._

  /* Validation stuff */
  protected override def validationStateClasses: ValidationStateClasses =
    new ValidationStateClasses {
      override def success: AttrPair = cls := "success"
      override def warning: AttrPair = cls := "warning"
      override def error: AttrPair   = cls := "error"
    }

  /** normal input "constructor", should override in impl */
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
  ): Frag = {
    val commonAttrs = Seq(inputType, inputName) ++ inputId.map(id := _) ++ inputAttrs
    val inputFrag =
      if (inputType.v == "textarea") textarea(commonAttrs)("")
      else input(commonAttrs)
    val inputFragTransformed = inputTransform(inputFrag)
    inputLabel match {
      case None => inputFragTransformed
      case Some(inputLabel) =>
        label(inputId.map(`for` := _))(
          inputFragTransformed,
          inputLabel
        )
    }
    div(inputValidationState.map(_.clazz))(
      inputFrag,
      inputMessages.map(span(_)),
      inputHelp.map(span(_))
    )
  }

  protected override def constructInputButton(
      inputType: AttrPair,
      inputId: Option[String],
      inputLabel: String,
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label#Buttons
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/button#Validation
    // no label, name, validation .. :/
    val commonAttrs = Seq(inputType) ++
      inputId.map(id := _) ++ inputAttrs
    input(commonAttrs)
  }

  protected override def constructButton(
      inputLabel: Frag,
      inputAttrs: Seq[AttrPair]
  ): Frag =
    button(inputAttrs)(inputLabel)

  /** checkbox input "constructor", should override in impl */
  protected override def constructInputCheckbox(
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox
    // no validation
    val commonAttrs = Seq(tpe := "checkbox", inputName) ++
      inputId.map(id := _) ++ inputAttrs
    val inputFrag = inputLabel match {
      case None => input(commonAttrs)
      case Some(inputLabel) =>
        label(inputId.map(`for` := _))(
          input(commonAttrs),
          inputLabel
        )
    }
    div(
      inputFrag,
      inputHelp.map(span(_))
    )
  }

  protected override def constructInputCheckboxes(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag =
    valueAndLabelAndAttrs.map { case (cbValue, cbLabel, inputAttrs) =>
      val commonAttrs = Seq(tpe := "checkbox", inputName, value := cbValue) ++ inputAttrs
      label(input(commonAttrs), cbLabel)
    }

  protected override def constructInputRadio(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag =
    valueAndLabelAndAttrs.map { case (radioValue, radioLabel, inputAttrs) =>
      val commonAttrs = Seq(tpe := "radio", inputName, value := radioValue) ++ inputAttrs
      label(input(commonAttrs), radioLabel)
    }

  protected override def constructInputSelect(
      inputName: AttrPair,
      inputId: Option[String],
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val optionFrags = valueAndLabelAndAttrs.map { case (optionValue, optionLabel, optionAttrs) =>
      val commonAttrs = Seq(value := optionValue) ++ optionAttrs
      option(commonAttrs)(optionLabel)
    }
    val selectAttrs = inputAttrs ++ Seq(inputName) ++ inputId.map(id := _)
    div(
      inputLabel.map(l => label(inputId.map(`for` := _))),
      select(selectAttrs)(optionFrags)
    )
  }

  // only possible attribute for <optgroup> is "disabled", so we dont bother...
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
    div(
      inputLabel.map(l => label(inputId.map(`for` := _))),
      select(selectAttrs)(optionGroupFrags)
    )
  }
}
