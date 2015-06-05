#!/bin/sh

SBT_LAUNCHER_VERSION=0.13.8
SBT_OPTS="-Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256M"

java $SBT_OPTS -jar `dirname $0`/sbt-launch-$SBT_LAUNCHER_VERSION.jar "$@"
