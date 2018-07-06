#!/bin/sh

echo "Running test 1 with list size = 10000, num. operations = 10000"
./RandomTestList 10000 10000

echo "Executing Unit Tests"
./UnitTestList
