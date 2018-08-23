# hepek

[![Join the chat at https://gitter.im/sake92/hepek](https://badges.gitter.im/sake92/hepek.svg)](https://gitter.im/sake92/hepek?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Docs
Click [here](https://sake92.github.io/hepek)

## Examples

- [official](https://github.com/sake92/hepek-examples)
- [source code](https://github.com/sake92/sake-ba-source) of sake.ba site

Deploy your first `hepek` project with Netlify with a few clicks:  

[![Deploy to Netlify](https://www.netlify.com/img/deploy/button.svg)](https://app.netlify.com/start/deploy?repository=https://github.com/sake92/hepek-starter)

## Features
- full Scala support, statically typed templates etc.
- **automatic relative paths**, with `ref` or `relTo` method, OMG!!!
- full Markdown support, [Commonmark](http://commonmark.org/) flavored
- Bootstrap3, PureCSS helpers
- Prismjs code highlighter (typesafe)
- KaTeX or Mathjax for math formulas
- form helpers
- **incremental** rendering, by default!
- file watching with sbt
- PDF rendering

## TODO
- dynamic pages support, e.g. replace Play's Twirl templates :)
- integrate more CSS and JS libraries/frameworks

## Changelog

### 0.1.2
- added PureCSS support ([#16](https://github.com/sake92/hepek/pull/16))
- added form support

### 0.1.1 (first release)
- basic Bootstrap3 support: dependencies, grid, navbar
- Markdown support via Commonmark
- Prismjs support
- Katex and Mathjax support
- PDF render support
