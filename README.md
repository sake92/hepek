# Hepek [![Maven Central](https://img.shields.io/maven-central/v/ba.sake/hepek_2.12.svg?style=flat-square&label=Scala+2.12)](https://mvnrepository.com/artifact/ba.sake/hepek) [![Build Status](	https://img.shields.io/travis/sake92/hepek/master.svg?logo=travis&style=flat-square)](https://travis-ci.org/sake92/hepek) [![Gitter](https://img.shields.io/gitter/room/sake92/hepek.svg?style=flat-square)](https://gitter.im/sake92/hepek?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Motivation? Typesafe HTML, but not too much.  
See this post also: [Why templates are dead](https://codeburst.io/80-of-my-coding-is-doing-this-or-why-templates-are-dead-b640fc149e22)

## Projects
- **Hepek-Components**, typesafe helpers built around Scalatags and various HTML/CSS/JS frameworks/libraries
- **Hepek**, static site generator
- **Hepek-Play**, Play framework integration

---
## Hepek-Components
Bunch of Scalatags helpers.  
Used in Hepek and Hepek-Play.  
Can be used in a standalone projects also:  
`"ba.sake" %% "hepek-components" % "0.4.0"`

These are some of the features:
- typesafe templates
- typesafe grids
- typesafe form inputs
- markdown
- code highlighting
- math


---
## Hepek
Static site generator for developers. 
Adds these on top of Hepek-Components:
- automatic relative paths
- incremental rendering
- PDF rendering

You can start with cloning the [starter template](https://github.com/sake92/hepek-starter),  
or fork && deploy with Netlify (yes, for free) in just a few clicks:  

[![Deploy to Netlify](https://www.netlify.com/img/deploy/button.svg)](https://app.netlify.com/start/deploy?repository=https://github.com/sake92/hepek-starter)

### Docs and examples
- [docs](https://sake92.github.io/hepek)
- [examples](https://github.com/sake92/hepek-examples)
- [source code](https://github.com/sake92/sake-ba-source) of sake.ba


---
## Hepek-Play
Adds these on top of Hepek-Components:
- writeable `HtmlPage`
- support for Play's `Field`s

## TODO
- ScalaJS components
- integrate more CSS and JS libraries/frameworks




