module Dice where

import System.Random

digs :: (Integral x) => x -> [x]
digs x
  | x <= 0 = []
  | otherwise = digs (x `div` 10) ++ [x `mod` 10]

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
rollMultiple x
  | x > 100 = return [] -- bei mehr lädt es ewig da wir rekursion nehmen
  | x < 0 = return []
  | otherwise =
      roll >>= \r ->
        rollMultiple (x - 1) >>= \rs ->
          return (r : rs)
