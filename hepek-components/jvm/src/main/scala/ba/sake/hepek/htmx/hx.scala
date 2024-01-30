package ba.sake.hepek.htmx

import ba.sake.hepek.scalatags.all.*

object hx {

  /** Fires a GET request to the specified URL */
  val get = attr("hx-get")

  /** Fires a POST request to the specified URL */
  val post = attr("hx-post")

  /** Fires a PUT request to the specified URL */
  val put = attr("hx-put")

  /** Fires a PATCH request to the specified URL */
  val patch = attr("hx-patch")

  /** Fires a DELETE request to the specified URL */
  val delete = attr("hx-delete")

  /** adds to the headers that will be submitted with the request */
  val headers = attr("hx-headers")

  /** JSON-formatted parameters that will be submitted with an AJAX request */
  val vals = attr("hx-vals")

  /** specifies the event that triggers the request */
  val trigger = attr("hx-trigger")

  /** specifies the target element to be swapped */
  val target = attr("hx-target")

  /** select content to swap in from a response */
  val select = attr("hx-select")

  /** select content to swap in from a response, out of band (somewhere other than the target)
    */
  val selectOob = attr("hx-select-oob")

  /** controls how content is swapped in (outerHTML, beforeend, afterend, ..) */
  val swap = attr("hx-swap")

  /** marks content in a response to be out of band (should swap in somewhere other than the target)
    */
  val swapOob = attr("hx-swap-oob")

  /** handle events with a inline scripts on elements */
  val on = attr("hx-on")

  /** add or remove progressive enhancement for links and forms */
  val boost = attr("hx-boost")

  /** pushes the URL into the browser location bar, creating a new history entry
    */
  val pushUrl = attr("hx-push-url")

  /** shows a confirm() dialog before firing a request */
  val confirm = attr("hx-confirm")

  /** disables htmx processing for the given node and any children nodes */
  val disable = attr("hx-disable")

  /** adds the disabled attribute to the specified elements while a request is in flight
    */
  val disabledElt = attr("hx-hx-disabled-elt")

  /** control and disable automatic attribute inheritance for child nodes */
  val disinherit = attr("hx-disinherit")

  /** changes the request encoding type */
  val encoding = attr("hx-encoding")

  /** extensions to use for this element. See https://htmx.org/extensions/
    */
  val ext = attr("hx-ext")

  /** prevent sensitive data being saved to the history cache */
  val history = attr("hx-history")

  /** the element to snapshot and restore during history navigation */
  val historyElt = attr("hx-history-elt")

  /** include additional data in requests */
  val include = attr("hx-include")

  /** the element to put the htmx-request class on during the request */
  val indicator = attr("hx-indicator")

  /** filters the parameters that will be submitted with a request */
  val params = attr("hx-params")

  /** specifies elements to keep unchanged between requests */
  val preserve = attr("hx-preserve")

  /** shows a prompt() before submitting a request */
  val prompt = attr("hx-prompt")

  /** replace the URL in the browser location bar */
  val replaceUrl = attr("hx-replace-url")

  /** configures various aspects of the request */
  val request = attr("hx-request")

  /** control how requests made by different elements are synchronized */
  val sync = attr("hx-sync")

  /** force elements to validate themselves before a request */
  val validate = attr("hx-validate")

  //////////////// SSE
  /** https://htmx.org/extensions/server-sent-events/ */
  val sseConnect = attr("sse-connect")

  /** https://htmx.org/extensions/server-sent-events/ */
  val sseSwap = attr("sse-swap")

  ////////////// WS
  /** https://htmx.org/extensions/web-sockets/ */
  val wsConnect = attr("ws-connect")

  /** https://htmx.org/extensions/web-sockets/ */
  val wsSend = attr("ws-send")

}
