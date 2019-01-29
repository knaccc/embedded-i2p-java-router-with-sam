#!/bin/bash

if [ $(uname -s) = Darwin ]; then
    basedir=$(dirname $(cd "$(dirname "$0")"; pwd -P))
else
    basedir=$(dirname $(dirname $(readlink -fm $0)))
fi

$basedir/bin/java -cp $basedir/i2p.base/jbigi.jar -m org.getmonero.i2p.zero.gui --i2p.dir.base=$basedir/i2p.base --i2p.dir.config=$basedir/i2p.config