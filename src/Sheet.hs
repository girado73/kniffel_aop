module Sheet where

import Data.List

-- Function to sum all elements if bool is true
sumIf :: (Num a) => [a] -> Bool -> a
sumIf xs b
  | b = sum xs
  | otherwise = 0

-- Function to count target in list
nummercounter :: [Int] -> Int -> Int
nummercounter xs x = x * length (filter (== x) xs)

-- Funktion to check if any element occurs exactly 4 times in a list
pasch4checker :: (Eq a) => [a] -> Bool
pasch4checker xs = length (filter (== head xs) xs) == 4 || length (filter (== xs !! 1) xs) == 4

-- Function to check if any element occurs exactly 3 times in a list
pasch3checker :: (Eq a) => [a] -> Bool
pasch3checker xs = any (\x -> length (filter (== x) xs) == 3) xs

-- Function to check for a fullHouse
fullHouseCheck :: (Eq a, Ord a) => [a] -> Bool
fullHouseCheck xs = sort (map length grouped) == [2, 3]
 where
  grouped = group (sort xs)

-- Function to check if list is "grStr"
grStrCheck :: (Ord a, Num a) => [a] -> Bool
grStrCheck xs = genericLength (nub xs) == 5 && sort xs == [1, 2, 3, 4, 5] || sort xs == [2, 3, 4, 5, 6]

-- Function to check if list is "klStr"
klStrCheck :: (Num a, Ord a) => [a] -> Bool
klStrCheck xs =
  let noDup = sort $ nub xs
   in noDup == [1, 2, 3, 4] || noDup == [2, 3, 4, 5] || noDup == [3, 4, 5, 6]

-- Function to check if all elements are the same
kniffelcheck :: (Ord a) => [a] -> Bool
kniffelcheck [] = False
kniffelcheck xs = minimum xs == maximum xs
