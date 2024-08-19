module Main where

import Brain
import Dice
import Sheet

main :: IO String
main = do
  dice <- Dice.rollMultiple 5
  putStrLn $ "Würfel: " ++ show dice
  sumVals <- Brain.printSumValues dice
  print sumVals
  Brain.giveProp dice
