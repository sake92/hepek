
# Hepek CLI

This is a small package that enables you to run Hepek SSG via scala-cli.

Create a file `runHepekCLI.scala` with the following content:
```scala
//> using dep ba.sake::hepek-cli:0.30.0
import ba.sake.hepek.cli.HepekCLI

@main def runHepekCLI() =
  HepekCLI().run()
```

and run it with `scala-cli runHepekCLI.scala`.

It will:
1. write all `object .. extends Renderable` from the `package files` to `hepek_output` folder
2. copy all files from `resources/public` to `hepek_output` folder

## Minimal example

```scala
package files // mandatory !!

import java.nio.file.Paths
import ba.sake.hepek.core.Renderable

object RenderMe extends Renderable {

  override def render =
    "Some text" // arbitrary Scala code
  
  override def relPath = 
    Paths.get("renderme.txt")
}
```

You'll find the `hepek_output/renderme.txt` file, with text `Some text`.