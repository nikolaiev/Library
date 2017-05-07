#!/usr/bin/env bash
for file in *.properties;
do
    cmd="native2ascii -encoding UTF-8 ${file} ${file}"
    #echo $cmd
    eval $cmd
done