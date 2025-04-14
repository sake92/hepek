package ba.sake.hepek.fusejs

import ba.sake.nodejs.script.executor.NodejsScriptExecutor.*
import ba.sake.tupson.*

object FusejsIndexBuilder {
  private val fusejsEnv = Environment(os.pwd / "tmp/hepek/nodejs-fusejs")
  
  def build(fusejsIndexedPagesData:  Seq[FusejsStaticPageData]): String = {
    execute(
      fusejsEnv,
      s"""
      |const Fuse = require('fuse.js')
      |const myIndex = Fuse.createIndex(['title', 'text'], ${fusejsIndexedPagesData.toJson})
      |const res = JSON.stringify(myIndex.toJSON());
      |console.log(res);
      """.stripMargin,
      Set(NpmDependency("fuse.js", Some("7.1.0")))
    )
  }
}
