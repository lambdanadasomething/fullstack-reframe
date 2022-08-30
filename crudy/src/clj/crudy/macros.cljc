(ns crudy.macros)

(defmacro code-for-nodejs
  [& body]
  (when false
    `(do ~@body)))

(defmacro code-for-browser
  [& body]
  (when-not false
    `(do ~@body)))
