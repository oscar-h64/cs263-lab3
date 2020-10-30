import System.Environment
import Control.Monad (forM_)

main :: IO()
main = do
    [p, g, toCrackCS, toCrackSC] <- map read <$> getArgs :: IO [Int]
    let keys = [(a,b) | a <- [1..p], b <- [1..p]]
    forM_ keys $ \(cs,sc) ->
        if (g^cs `mod` p == toCrackCS) && (g^sc `mod` p == toCrackSC)
        then print (cs,sc) >> print (toCrackCS^sc `mod` p) >> print (toCrackSC^cs `mod` p)
        else pure ()
