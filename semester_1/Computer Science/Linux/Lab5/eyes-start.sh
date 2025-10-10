#!/bin/bash

period_m=$1
period_s=$((period_m*60))
period_ms=$((period_s*1000))
icon=~/Desktop/images/10470_2005.jpg

notify-send -i $icon -t $period_ms "Time to relax, kroko!" "$period_m minutes"
