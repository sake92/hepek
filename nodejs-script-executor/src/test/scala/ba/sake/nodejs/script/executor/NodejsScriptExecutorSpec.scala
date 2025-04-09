package ba.sake.nodejs.script.executor

import ba.sake.nodejs.script.executor.NodejsScriptExecutor.*

class NodejsScriptExecutorSpec extends munit.FunSuite {
  test("Hello NodeJS") {
    val res = executeFresh(
      """
        |console.log("Hello NodeJS!")
        |""".stripMargin
    )
    assertEquals(res.trim, "Hello NodeJS!")
  }
//
  test("CommonJS module with deps (katex math highlighter)") {
    val res = executeFresh(
      """
         const katex = require('katex');
         
         const html = katex.renderToString('\Eta\Epsilon\pi\Epsilon\Kappa');
         
         console.log(html);
       """,
      Set(NpmDependency("katex", Some("0.16.21")))
    )
    val expected =
      """<span class="katex"><span class="katex-mathml"><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>E</mi><mi>t</mi><mi>a</mi><mi>E</mi><mi>p</mi><mi>s</mi><mi>i</mi><mi>l</mi><mi>o</mi><mi>n</mi><mi>p</mi><mi>i</mi><mi>E</mi><mi>p</mi><mi>s</mi><mi>i</mi><mi>l</mi><mi>o</mi><mi>n</mi><mi>K</mi><mi>a</mi><mi>p</mi><mi>p</mi><mi>a</mi></mrow><annotation encoding="application/x-tex">EtaEpsilonpiEpsilonKappa</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:0.8889em;vertical-align:-0.1944em;"></span><span class="mord mathnormal">Et</span><span class="mord mathnormal">a</span><span class="mord mathnormal">Ep</span><span class="mord mathnormal">s</span><span class="mord mathnormal">i</span><span class="mord mathnormal" style="margin-right:0.01968em;">l</span><span class="mord mathnormal">o</span><span class="mord mathnormal">n</span><span class="mord mathnormal">p</span><span class="mord mathnormal">i</span><span class="mord mathnormal">Ep</span><span class="mord mathnormal">s</span><span class="mord mathnormal">i</span><span class="mord mathnormal" style="margin-right:0.01968em;">l</span><span class="mord mathnormal">o</span><span class="mord mathnormal">n</span><span class="mord mathnormal" style="margin-right:0.07153em;">K</span><span class="mord mathnormal">a</span><span class="mord mathnormal">pp</span><span class="mord mathnormal">a</span></span></span></span>"""
    assertEquals(res.trim, expected)
  }

  test("ES6 module with deps (shiki highlighter)") {
    val res = executeFresh(
      """
        import { codeToHtml } from 'shiki';
    
        const code = 'const a = 1';
        const html = await codeToHtml(code, {
          lang: 'javascript',
          theme: 'vitesse-dark'
        });
        
        console.log(html);
      """,
      Set(NpmDependency("shiki", Some("3.2.1"))),
      scriptName = "script.mjs" // must explicitly use .mjs for ES6 modules
    )
    val expected =
      """<pre class="shiki vitesse-dark" style="background-color:#121212;color:#dbd7caee" tabindex="0"><code><span class="line"><span style="color:#CB7676">const</span><span style="color:#BD976A"> a</span><span style="color:#666666"> =</span><span style="color:#4C9A91"> 1</span></span></code></pre>"""
    assertEquals(res.trim, expected)
  }
}
