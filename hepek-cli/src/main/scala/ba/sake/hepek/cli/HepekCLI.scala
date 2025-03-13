package ba.sake.hepek.cli

import java.io.File
import java.lang.reflect.Modifier
import java.net.URLClassLoader
import java.nio.file.{Paths, Path as JPath}
import scala.jdk.CollectionConverters.*

@main def HepekCliApp() =
  HepekCLI().run()

class HepekCLI {

  private val destFolder = os.pwd / "hepek_output"

  private val ModuleFieldName = "MODULE$"
  private val RenderableFQN = "ba.sake.hepek.core.Renderable"
  private val MultiRenderableFQN = "ba.sake.hepek.core.MultiRenderable"

  def run(): Unit = {
    os.remove.all(destFolder)
    
    val fullClasspath = os.call(cmd = ("scala-cli", "compile", "--print-class-path", "."))
      .out.text().split(File.pathSeparator).map(_.trim)
    val classloader = new URLClassLoader(fullClasspath.map(Paths.get(_).toUri.toURL))

    // collect only the user classes
    val userClassFilesFolder = os.Path(fullClasspath.head) // first is always the user classes folder, I think :D
    val userClassFiles = if (os.exists(userClassFilesFolder)) {
      os.walk(userClassFilesFolder)
        .filter(x => os.isFile(x, followLinks = false) && x.ext == "class")
    } else Seq.empty

    // do rendering
    val renderableClazz = classloader.loadClass(RenderableFQN)
    val multiRenderableClazz = classloader.loadClass(MultiRenderableFQN)
    val userClassNames = userClassFiles
      .map(_.relativeTo(userClassFilesFolder))
      .map(
        _.toString
          .dropRight(6) // remove ".class" suffix
          .replaceAll("\\\\|/", "\\.") // replace "\" and "/" with "."
      )
      .filter(_.startsWith("files.")) // only those in "files" package !!!

    userClassNames.foreach { className =>
      val clazz = classloader.loadClass(className)
      val mods = clazz.getModifiers
      val fieldNames = clazz.getDeclaredFields.map(_.getName).toSeq

      val isScalaObject =
        !Modifier.isAbstract(mods) && fieldNames.contains(ModuleFieldName)
      if (isScalaObject) {
        if (isSuperclassOf(renderableClazz, clazz)) {
          val objClazz =
            classloader.loadClass(className.dropRight(1)) // without $ at end
          val content =
            objClazz.getMethod("render").invoke(null).asInstanceOf[String]
          val relPath =
            objClazz.getMethod("relPath").invoke(null).asInstanceOf[JPath]
          writeRenderableObject(className, destFolder, relPath, content)
        } else if (isSuperclassOf(multiRenderableClazz, clazz)) {
          val objClazz =
            classloader.loadClass(className.dropRight(1)) // without $ at end
          val renderables = objClazz
            .getMethod("renderables")
            .invoke(null)
            .asInstanceOf[java.util.List[_]]
          renderables.asScala.foreach { r =>
            val content =
              renderableClazz.getMethod("render").invoke(r).asInstanceOf[String]
            val relPath =
              renderableClazz.getMethod("relPath").invoke(r).asInstanceOf[JPath]
            writeRenderableObject(className, destFolder, relPath, content)
          }
        }
      }
    }

    // copy public files
    val publicFolder = os.pwd / "resources/public"
    if os.exists(publicFolder) then
      os.copy(
        publicFolder,
        destFolder,
        createFolders = true,
        replaceExisting = true,
        mergeFolders = true
      )
  }

  private def isSuperclassOf(clazzParent: Class[_], clazz: Class[_]): Boolean =
    clazzParent.isAssignableFrom(clazz)

  private def writeRenderableObject(className: String, destFolder: os.Path, relPath: JPath, content: String): Unit = {
    val finalPath = destFolder / os.RelPath(relPath)
    println(s"Rendering '${className}' to '${finalPath}'")
    os.write.over(finalPath, content, createFolders = true)
  }

}

