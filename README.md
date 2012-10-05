# lein-nrepl-eval

A marginally useful plugin to eval some code in a remote nREPL
session. Only tested with Leiningen 2.x.

## Usage

Use this for user-level plugins:

Put `[lein-nrepl-eval "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your
`:user` profile.

Use this for project-level plugins:

Put `[lein-nrepl-eval "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your project.clj.

Example usage:

    $ lein help nrepl-eval
    
    $ lein nrepl-eval "(require '[clojure.string :as s])"
    
    $ lein nrepl-eval '(s/join ", " ["hello" "world"])'
    
You must be careful with quoting at the shell, of course. In most
shells, a single quote may not occur between single quotes, even when
preceded by a backslash. You may have to use (quote ...) instead of a
single-quote.

## License

Copyright © 2012 Jim Crossley

Distributed under the Eclipse Public License, the same as Clojure.
