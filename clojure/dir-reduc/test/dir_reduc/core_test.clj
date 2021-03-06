(ns dir-reduc.core-test
  (:require [clojure.test :refer :all]
            [dir-reduc.core :refer :all]))

(deftest non-reducible-steps
  (testing "single step"
    (is (= (dirReduc ["NORTH"]) ["NORTH"])))
  (testing "multiple steps"
    (is (= (dirReduc ["NORTH", "EAST"]) ["NORTH", "EAST"])))
  )

(deftest two-reducible-steps-at-the-beginning
  (testing "NORTH annihilates SOUTH"
    (is (nil? (dirReduc ["NORTH", "SOUTH"]))))
  (testing "SOUTH annihilates NORTH"
    (is (= (dirReduc ["SOUTH", "NORTH", "EAST"]) ["EAST"])))
  (testing "EAST annihilates WEST"
    (is (= (dirReduc ["EAST", "WEST", "EAST", "NORTH"]) ["EAST", "NORTH"])))
  (testing "WEST annihilates EAST"
    (is (nil? (dirReduc ["WEST", "EAST"]))))
  )

(deftest keep-reducing-at-the-beginning
  (testing "consecutive NORTH SOUTH at the beginning"
    (is (nil? (dirReduc ["NORTH", "SOUTH", "NORTH", "SOUTH"]))))
  )

(deftest skip-1st-position-and-keep-reducing
    (testing "reduction not at the beginning"
    (is (= (dirReduc ["EAST", "SOUTH", "NORTH", "EAST"]) ["EAST", "EAST"])))
  )

(deftest a-test1
  (testing "Test 1"
    (def ur ["NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"])
    (def vr ["WEST"])
    (is (= (dirReduc ur) vr))))
