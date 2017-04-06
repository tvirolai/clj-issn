# clj-issn

[![Clojars Project](https://img.shields.io/clojars/v/clj-issn.svg)](https://clojars.org/clj-issn)
[![Build Status](https://travis-ci.org/tvirolai/clj-issn.svg?branch=master)](https://travis-ci.org/tvirolai/clj-issn)

A tiny Clojure library for validating and formatting ISSN codes.

## Installation

Add `clj-issn` to your `project.clj` the usual way:

```clojure
[clj-issn "0.1.0"]
```

Then require it to your namespace:

```clojure
(ns foo.bar
  (:require [clj-issn.core :as issn]))
```

## Usage

`clj-issn` provides three functions that you may need.

First, check digit calculation:

```clojure
(issn/check-digit "2343-472")
=> 4
(issn/check-digit "2057-3189")
=> 9
(issn/check-digit "123-HELLO")
=> nil
```

Second, ISSN code formatting:

```clojure
(issn/format-issn "1050124x")
=> "1050-124X"
```

Third, code validation:

```clojure
(issn/is-valid? "1050-124X")
=> true
(issn/is-valid? "1050-1244")
=> false
```

## License

Copyright © 2017 Tuomo Virolainen

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
