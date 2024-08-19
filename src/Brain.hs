module Brain where

import Data.List
import Data.Maybe
import Sheet

options =
  [ "Einsen"
  , "Zweien"
  , "Dreien"
  , "Vieren"
  , "Fuenfen"
  , "Sechsen"
  , "Dreierpasch"
  , "Viererpasch"
  , "Full House"
  , "Kleine Strasse"
  , "Grosse Strasse"
  , "Kniffel"
  , "Chance"
  ]

getSumValues :: [Int] -> [Int]
getSumValues xs =
  [nummercounter xs i | i <- [1 .. 6]]
    ++ sumIf xs (pasch3checker xs)
    : sumIf xs (pasch4checker xs)
    : sumIf xs (fullHouseCheck xs)
    : sumIf xs (klStrCheck xs)
    : sumIf xs (grStrCheck xs)
    : sumIf xs (kniffelcheck xs)
    : [sum xs]

printSumValues :: [Int] -> IO [String]
printSumValues xs = do
  let sumValues = getSumValues xs
  return [x ++ ": " ++ show y | (x, y) <- zip options sumValues]

giveProp :: [Int] -> IO String
giveProp xs = do
  let sumVal = init $ getSumValues xs
  let maxVal = maximum sumVal
  print $ elemIndex maxVal sumVal
  return $ options !! fromJust (elemIndex maxVal sumVal) ++ ": " ++ show maxVal
