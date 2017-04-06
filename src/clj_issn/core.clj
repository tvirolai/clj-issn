(ns clj-issn.core
  (:require [clojure.string :as s]))

(defn- first-7-digits [issn]
  (let [digits (->> issn 
                    (re-seq #"\d")
                    (take 7)
                    (map #(Integer. %)))]
    (if (not= 7 (count digits))
      nil
      digits)))

(defn no-erroneous-chars? 
  "Fails if ISSN contains e.g. alphabetical chars
  in other than the last position."
  [issn]
  (boolean (->> issn drop-last s/join first-7-digits)))

(defn check-digit 
  "Takes an ISSN (or its seven first digits)
  and returns a check digit. The digit can also
  be an X, which is returned as string." 
  [issn]
  (let [sum (->> issn 
                 first-7-digits 
                 (map #(* %1 %2) (range 8 1 -1)) 
                 (reduce +))
        remainder (mod sum 11)]
    (if (zero? sum)
      nil
      (case remainder
        0 0
        1 "X"
        (- 11 remainder)))))

(defn format-issn
  "Takes an ISSN code and returns a properly formatted version
  of it."
  [issn]
  (let [chrs (-> issn
                 first-7-digits 
                 vec 
                 (conj (check-digit issn)))]
    (->> chrs
         (partition 4)
         (map s/join)
         (s/join "-"))))

(defn is-valid? 
  "Check validity of ISSN."
  [issn]
  (and
    (= issn (format-issn issn))
    (no-erroneous-chars? issn)))
