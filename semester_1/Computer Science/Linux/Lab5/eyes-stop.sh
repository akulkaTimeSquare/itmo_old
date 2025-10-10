#!/bin/bash

relax_time_m=1
relax_time_s=$((relax_time_m*60))
sleep $relax_time_s

icon=~/Desktop/images/10470_2005.jpg
period_ms=5000
notify-send -i $icon -t $period_ms "Time to get back to work, kroko!"
