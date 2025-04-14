package ba.sake.hepek.fusejs

import ba.sake.nodejs.script.executor.NodejsScriptExecutor
import ba.sake.nodejs.script.executor.NodejsScriptExecutor.NpmDependency
import ba.sake.tupson.*

object FusejsIndexBuilder {
  private val fusejsExecutor = NodejsScriptExecutor(
    os.pwd / "tmp/hepek/nodejs-fusejs",
    Seq(NpmDependency("fuse.js", Some("7.1.0")))
  )

  def build(fusejsIndexedPagesData: Seq[FusejsStaticPageData]): String =
    fusejsExecutor.executeScript(
      s"""
      |const Fuse = require('fuse.js')
      |const myIndex = Fuse.createIndex(['title', 'text'], ${fusejsIndexedPagesData.toJson})
      |const res = JSON.stringify(myIndex.toJSON());
      |console.log(res);
      """.stripMargin
    )
}
