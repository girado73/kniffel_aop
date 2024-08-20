module Main where

import Data.List
import Dice
import GHC.IO (unsafePerformIO)
import Sheet
import Test.QuickCheck

-- Property: grStrCheck should return True if the list has exactly 5 unique elements
prop_grStrCheck :: [Int] -> Bool
prop_grStrCheck xs =
  if genericLength (nub xs) == 5 && all (\r -> r > 0 && r < 7) xs
    then grStrCheck xs
    else not (grStrCheck xs)

{-
monadRollMultipleCheck :: Int -> IO Bool
monadRollMultipleCheck x = do
  xs <- rollMultiple x
  return $ length xs == x
-}

-- TODO wir müssen hier unsafePerformIO benutzen damit quickCheck unsere Funktionen checkt
-- tests if rollMultiple x == length $ rollMultiple x
prop_rollMultipleCheck :: Int -> Bool
prop_rollMultipleCheck x
  | x < 0 || x > 100 = null (unsafePerformIO (rollMultiple x))
  | otherwise = length (unsafePerformIO (rollMultiple x)) == x

-- tests if roll generates a number between 1 and 6
test_roll :: Bool
test_roll = unsafePerformIO roll <= 5 && unsafePerformIO roll >= 1

test_digs :: Int -> Bool
test_digs x
  | x < 0 = null (digs x)
  | otherwise =
      let len = genericLength (digs x)
       in x `mod` 10 ^ len == x

-- Run QuickCheck to test the property
main :: IO ()
main = do
  quickCheckResult prop_rollMultipleCheck
    >>= \r ->
      print r
        >> quickCheckResult prop_grStrCheck
        >>= \r ->
          print r
            >> quickCheckResult test_digs
            >>= \r ->
              print r
