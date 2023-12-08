package ba.sake.hepek.zio

import zio.http.*
import ba.sake.hepek.html.HtmlPage

extension (res: Response.type) {
  def html(data: HtmlPage, status: Status = Status.Ok): Response =
    Response
      .text("<!DOCTYPE html>" + data.contents.render)
      .withStatus(status)
      .withHeader(Header.ContentType(MediaType.text.html).untyped)
}

/* RC4
extension (res: Response.type) {
  def html(data: HtmlPage, status: Status = Status.Ok): Response =
    Response
      .text("<!DOCTYPE html>" + data.contents.render)
      .addHeader(Header.ContentType(MediaType.text.html).untyped)
      .status(status)
}
 */
