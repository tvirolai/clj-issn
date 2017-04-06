(ns clj-issn.core-test
  (:require [clojure.test :refer :all]
            [clj-issn.core :refer :all]))

(def valid-codes ["1050-124X" "0378-5955" "0317-8471"])

(def invalid-codes ["1050-124!" "0378-5951" "03178471"])

(deftest checkdigit
  (testing "Check digit calculation works"
    (is (= 6 (check-digit "1613-9216"))
    (is (= 9 (check-digit "0021-8979"))
    (is (= 0 (check-digit "1089-7550"))
    (is (= "X" (check-digit "1050-124X"))
    (is (= nil (check-digit "1249XDFJ"))
    (is (= 2 (check-digit "1558-8432"))))))))))

(deftest validity
  (testing "Check if input codes are recognized as valid"
    (is (true? 
          (every? true? (map is-valid? valid-codes)))))
  (testing "Check if invalid codes fail validation"
    (is (true?
          (every? false? (map is-valid? invalid-codes))))))

(deftest characters
  (testing "If ISSN contains erroneous characters"
    (is (false? (no-erroneous-chars? "123D-1234")))
    (is (true? (no-erroneous-chars? "12341234")))
    (is (true? (no-erroneous-chars? "1234-567X")))))
