#!/bin/bash
# adapted from Katherine Giron Pe, OMSCS Fall 2018

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

wrong=0
total=0

for file in *Topo.py;
do
    len=`echo "$file" | wc -c`
    prefix=`expr $len - 4`
    class=`echo $file | cut -c1-$prefix | perl -pe 's/(^|_)([a-z\dA-Z])/uc($2)/ge'`
    python run_spanning_tree.py $class Logs/$class.log
    if ! diff -q --strip-trailing-cr Answers/$class.log Logs/$class.log; then
      wrong=$((wrong+1))
      echo -e "${RED}$file${NC}"
      diff --strip-trailing-cr Answers/$class.log Logs/$class.log
    else
      echo -e "${GREEN}$file${NC}"
    fi
    total=$((total+1))
done

echo "Number Wrong: $wrong"
echo "Test Cases Passed: $((total-wrong))/$total"