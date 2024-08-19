module Dice where

import System.Random

digs :: (Integral x) => x -> [x]
digs 0 = []
digs x = digs (x `div` 10) ++ [x `mod` 10]

roll :: IO Int
roll = do
  gen <- newStdGen
  let (randomNumber, _) = randomR (1, 6) gen
  return randomNumber

{- do Notation
rollMultiple :: Int -> IO [Int]
rollMultiple 0 = return []
rollMultiple x = do
  r <- roll
  rs <- rollMultiple (x - 1)
  return (r : rs)
-}

-- bind Notation
rollMultiple :: Int -> IO [Int]
rollMultiple 0 = return []
rollMultiple x =
  roll >>= \r ->
    rollMultiple (x - 1) >>= \rs ->
      return (r : rs)
