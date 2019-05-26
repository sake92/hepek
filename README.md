# Hepek [![Maven Central](https://img.shields.io/maven-central/v/ba.sake/hepek_2.12.svg?style=flat-square&label=Scala+2.12)](https://mvnrepository.com/artifact/ba.sake/hepek) [![Build Status](	https://img.shields.io/travis/sake92/hepek/master.svg?logo=travis&style=flat-square)](https://travis-ci.org/sake92/hepek) [![Gitter](https://img.shields.io/gitter/room/sake92/hepek.svg?style=flat-square)](https://gitter.im/sake92/hepek?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Projects
- **Hepek-Components**, typesafe helpers built around Scalatags and other HTML/CSS/JS frameworks/libraries
- **Hepek**, static site generator
- **Hepek-Play**, Play framework integration for Hepek-Components

---
## Hepek-Components
These are used in both Hepek and Hepek-Play.  
Can be used in a standalone projects also:  
`"ba.sake" %% "hepek-components" % "0.3.1+41-df1d576a-SNAPSHOT"`

These are some of the features:
- statically typed templates
- typesafe grids
- typesafe form helpers
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

You can tart with cloning the [starter template](https://github.com/sake92/hepek-starter),  
or deploy+fork it with Netlify (yes, for free) in just a few clicks:  

[![Deploy to Netlify](https://www.netlify.com/img/deploy/button.svg)](https://app.netlify.com/start/deploy?repository=https://github.com/sake92/hepek-starter)

### Docs and examples
- [docs](https://sake92.github.io/hepek)
- [examples](https://github.com/sake92/hepek-examples)
- [source code](https://github.com/sake92/sake-ba-source) of sake.ba


---
## Hepek-Play
Contains additional goodies for easier integration with Play framework:
- write response using hepek's `HtmlPage` without boilerplate
- integration with Play's `Field`s

## TODO
- ScalaJS components
- integrate more CSS and JS libraries/frameworks




